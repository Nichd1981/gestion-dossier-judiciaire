package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.exceptions.EntityNotFoundException;
import be.tftic.java.bll.services.JudgmentService;
import be.tftic.java.bll.specifications.JudgmentSpecification;
import be.tftic.java.common.models.requests.filter.JudgmentFilterRequest;
import be.tftic.java.common.models.requests.update.JudgmentUpdateRequest;
import be.tftic.java.common.models.responses.JudgmentResponse;
import be.tftic.java.dal.repositories.JudgmentRepository;
import be.tftic.java.dal.repositories.ComplaintRepository;
import be.tftic.java.domain.entities.Judgment;
import be.tftic.java.domain.entities.Complaint;
import be.tftic.java.domain.enums.JudgmentDecision;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe de service pour la gestion des opérations liées à l'entité Jugement.
 * Cette classe fournit une couche d'abstraction entre la couche de contrôleur et la couche de persistance,
 * permettant de gérer les opérations métier et de maintenir une séparation des préoccupations.
 *
 * @Service indique que cette classe est un composant Spring géré par le conteneur d'injection de dépendances.
 * Spring s'occupe de créer une instance unique de cette classe et de la fournir là où elle est nécessaire.
 * @RequiredArgsConstructor indique que le constructeur généré par Lombok ne prend en compte que les attributs finaux.
 * Dans ce cas, cela signifie que le constructeur injecte les instances de JugementRepository et PlainteRepository fournies par Spring.
 * JugementService indique que cette classe implémente l'interface JugementService,
 * ce qui permet de garantir que les méthodes nécessaires sont fournies et facilite le remplacement ou l'extension de l'implémentation.
 */
@Service
@RequiredArgsConstructor
public class JudgmentServiceImpl implements JudgmentService {

    private final JudgmentRepository judgmentRepository;
    private final ComplaintRepository complaintRepository;

    /**
     * Crée un nouveau jugement pour une plainte donnée.
     * La date du jugement est définie à la date et l'heure actuelles.
     *
     * @param plainteId l'identifiant unique de la plainte pour laquelle créer le jugement.
     */
    @Override
    public void create(Long plainteId) {
        Judgment judgment = new Judgment();
        judgment.setJudgmentDate(LocalDateTime.now());
        judgment.setComplaint(getComplaint(plainteId));
        judgmentRepository.save(judgment);
    }

    /**
     * Récupère tous les jugements associés à une plainte donnée.
     *
     * @param complaintId l'identifiant unique de la plainte pour laquelle récupérer les jugements.
     * @return la liste des jugements associés à la plainte, ou une liste vide si aucun jugement n'est associé.
     */
    @Override
    public List<JudgmentResponse> findAllForComplaint(Long complaintId) {
        JudgmentFilterRequest request = new JudgmentFilterRequest();
        request.setComplaintId(complaintId);
        return this.findWithCriteria(request);
    }

    /**
     * Récupère les jugements qui correspondent aux critères de recherche donnés.
     * Les critères de recherche incluent un identifiant ou un numéro de dossier de plainte, une borne inférieure de date, une borne supérieure de date, un mot-clé et une décision.
     * Les jugements sont filtrés en fonction des critères fournis, et seuls les jugements correspondants sont renvoyés.
     *
     * @return la liste des jugements qui correspondent aux critères de recherche donnés, ou une liste vide si aucun jugement ne correspond.
     */
    @Override
    public List<JudgmentResponse> findWithCriteria(JudgmentFilterRequest request) {
        Complaint complaint = (request.getComplaintId() != null ? getComplaint(request.getComplaintId()) : getComplaint(request.getNumberFileComplaint()));
        Specification<Judgment> spec = getSpecification(
                complaint,
                request.getDateLowerBound(),
                request.getDateUpperBound(),
                request.getKeywords(),
                request.getDecision());

        return judgmentRepository.findAll(spec)
                .stream()
                .map(JudgmentResponse::fromEntity)
                .toList();
    }

    /**
     * Met à jour la décision et le commentaire d'un jugement donné.
     * Si le jugement n'existe pas, une exception RuntimeException est levée.
     *
     * @param judgment les informations de mise à jour pour le jugement, y compris l'identifiant unique du jugement, la décision et le commentaire.
     */
    @Override
    public void closedJudgment(JudgmentUpdateRequest judgment) {
        Judgment toUpdate = judgmentRepository.findById(judgment.getId()).orElseThrow(
                () -> new RuntimeException("Le jugement n'existe pas")
        );

        toUpdate.setCommentary(judgment.getCommentary());
        toUpdate.setJudgmentDecision(JudgmentDecision.valueOf(judgment.getDecision()));
        judgmentRepository.save(toUpdate);
    }

    /**
     * Récupère une plainte donnée à partir de son identifiant unique.
     * Si la plainte n'existe pas, une exception RuntimeException est levée.
     *
     * @param complaintId l'identifiant unique de la plainte à récupérer.
     * @return la plainte correspondant à l'identifiant unique donné.
     */
    private Complaint getComplaint(Long complaintId){
        return complaintRepository.findById(complaintId).orElseThrow(
                () -> new EntityNotFoundException("Complaint not found")
        );
    }

    private Judgment getJudgment(Long judgmentId){
        return judgmentRepository.findById(judgmentId).orElseThrow(
                () -> new EntityNotFoundException("Judgement not found")
        );
    }

    /**
     * Récupère une plainte donnée à partir de son numéro de dossier.
     * Si la plainte n'existe pas, une exception RuntimeException est levée.
     *
     * @param fileNumber le numéro de dossier de la plainte à récupérer.
     * @return la plainte correspondant au numéro de dossier donné.
     */
    private Complaint getComplaint(String fileNumber) {
        return complaintRepository.findByFileNumber(fileNumber).orElseThrow(
                () -> new EntityNotFoundException("Complaint not found")
        );
    }

    /**
     * Construit une spécification pour le filtrage des jugements en fonction des critères de recherche donnés.
     * Les critères de recherche incluent un identifiant ou un numéro de dossier de plainte, une borne inférieure de date, une borne supérieure de date, un mot-clé et une décision.
     * La spécification est construite en combinant les différents critères à l'aide de l'opérateur logique AND.
     *
     * @param complaint l'identifiant unique ou le numéro de dossier de la plainte pour laquelle récupérer les jugements, ou null si ce critère est ignoré.
     * @param lowerBound la borne inférieure de date à utiliser pour le filtrage des jugements, ou null si ce critère est ignoré.
     * @param upperBound la borne supérieure de date à utiliser pour le filtrage des jugements, ou null si ce critère est ignoré.
     * @param keyWord le mot-clé à utiliser pour le filtrage des jugements, ou null si ce critère est ignoré.
     * @param decision la décision à utiliser pour le filtrage des jugements, ou null si ce critère est ignoré.
     * @return la spécification pour le filtrage des jugements en fonction des critères de recherche donnés.
     */
    private Specification<Judgment> getSpecification(Complaint complaint, LocalDate lowerBound, LocalDate upperBound, String keyWord, String decision) {
        Specification<Judgment> spec = Specification.where(null);
        if (complaint != null) {
            spec = spec.and(JudgmentSpecification.getByComplaint(complaint));
        }
        if (lowerBound != null) {
            spec = spec.and(JudgmentSpecification.getByDateLowerBound(lowerBound));
        }
        if (upperBound != null) {
            spec = spec.and(JudgmentSpecification.getByDateUpperBound(upperBound));
        }
        if (decision != null && !keyWord.isBlank()) {
            spec = spec.and(JudgmentSpecification.getByKeyword(keyWord));
        }

        if (decision != null && !decision.isBlank()) {
            spec = spec.and(JudgmentSpecification.getByDecision(JudgmentDecision.valueOf(decision)));
        }
        return spec;
    }
}
