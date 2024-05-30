package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.JugementService;
import be.tftic.java.bll.specifications.JugementSpecification;
import be.tftic.java.common.models.requests.update.JugementUpdateRequest;
import be.tftic.java.common.models.responses.JugementResponse;
import be.tftic.java.common.models.requests.JugementUpdateRequest;
import be.tftic.java.dal.repositories.JugementRepository;
import be.tftic.java.dal.repositories.PlainteRepository;
import be.tftic.java.domain.entities.Jugement;
import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.enums.JugementDecision;
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
public class JugementServiceImpl implements JugementService {

    private final JugementRepository jugementRepository;
    private final PlainteRepository plainteRepository;

    /**
     * Crée un nouveau jugement pour une plainte donnée.
     * La date du jugement est définie à la date et l'heure actuelles.
     *
     * @param plainteId l'identifiant unique de la plainte pour laquelle créer le jugement.
     */
    @Override
    public void create(Long plainteId) {
        Jugement jugement = new Jugement();
        jugement.setDateJugement(LocalDateTime.now());
        jugement.setPlainte(getPlainte(plainteId));
        jugementRepository.save(jugement);
    }

    /**
     * Récupère tous les jugements associés à une plainte donnée.
     *
     * @param plainteId l'identifiant unique de la plainte pour laquelle récupérer les jugements.
     * @return la liste des jugements associés à la plainte, ou une liste vide si aucun jugement n'est associé.
     */
    @Override
    public List<JugementResponse> findAllForPlainte(Long plainteId) {
        return this.findWithCriteria(plainteId, null, null, null, null, null);
    }

    /**
     * Récupère les jugements qui correspondent aux critères de recherche donnés.
     * Les critères de recherche incluent un identifiant ou un numéro de dossier de plainte, une borne inférieure de date, une borne supérieure de date, un mot-clé et une décision.
     * Les jugements sont filtrés en fonction des critères fournis, et seuls les jugements correspondants sont renvoyés.
     *
     * @param plainteId l'identifiant unique de la plainte pour laquelle récupérer les jugements, ou null si le numéro de dossier est utilisé.
     * @param numeroDossier le numéro de dossier de la plainte pour laquelle récupérer les jugements, ou null si l'identifiant est utilisé.
     * @param lowerBound la borne inférieure de date à utiliser pour le filtrage des jugements, ou null si ce critère est ignoré.
     * @param upperBound la borne supérieure de date à utiliser pour le filtrage des jugements, ou null si ce critère est ignoré.
     * @param keyWord le mot-clé à utiliser pour le filtrage des jugements, ou null si ce critère est ignoré.
     * @param decision la décision à utiliser pour le filtrage des jugements, ou null si ce critère est ignoré.
     * @return la liste des jugements qui correspondent aux critères de recherche donnés, ou une liste vide si aucun jugement ne correspond.
     */
    @Override
    public List<JugementResponse> findWithCriteria(Long plainteId, String numeroDossier, LocalDate lowerBound, LocalDate upperBound, String keyWord, String decision) {
        Plainte plainte = (plainteId != null ? getPlainte(plainteId) : getPlainte(numeroDossier));
        Specification<Jugement> spec = getSpecification(plainte, lowerBound, upperBound, keyWord, decision);

        return jugementRepository.findAll(spec)
                .stream()
                .map(JugementResponse::fromEntity)
                .toList();
    }

    /**
     * Met à jour la décision et le commentaire d'un jugement donné.
     * Si le jugement n'existe pas, une exception RuntimeException est levée.
     *
     * @param jugement les informations de mise à jour pour le jugement, y compris l'identifiant unique du jugement, la décision et le commentaire.
     */
    @Override
    public void cloturerJugement(JugementUpdateRequest jugement) {
        Jugement toUpdate = jugementRepository.findById(jugement.getId()).orElseThrow(
                () -> new RuntimeException("Le jugement n'existe pas")
        );

        toUpdate.setCommentaire(jugement.getCommentaire());
        toUpdate.setJugementDecision(JugementDecision.valueOf(jugement.getDecision()));
        jugementRepository.save(toUpdate);
    }

    /**
     * Récupère une plainte donnée à partir de son identifiant unique.
     * Si la plainte n'existe pas, une exception RuntimeException est levée.
     *
     * @param plainteId l'identifiant unique de la plainte à récupérer.
     * @return la plainte correspondant à l'identifiant unique donné.
     */
    private Plainte getPlainte(Long plainteId) {
        return plainteRepository.findById(plainteId)
                .orElseThrow(
                        () -> new RuntimeException("Plainte n'existe pas")
                );
    }

    /**
     * Récupère une plainte donnée à partir de son numéro de dossier.
     * Si la plainte n'existe pas, une exception RuntimeException est levée.
     *
     * @param numeroDossier le numéro de dossier de la plainte à récupérer.
     * @return la plainte correspondant au numéro de dossier donné.
     */
    private Plainte getPlainte(String numeroDossier) {
        return plainteRepository.findByNumeroDossier(numeroDossier)
                .orElseThrow(
                        () -> new RuntimeException("Plainte n'existe pas")
                );
    }

    /**
     * Construit une spécification pour le filtrage des jugements en fonction des critères de recherche donnés.
     * Les critères de recherche incluent un identifiant ou un numéro de dossier de plainte, une borne inférieure de date, une borne supérieure de date, un mot-clé et une décision.
     * La spécification est construite en combinant les différents critères à l'aide de l'opérateur logique AND.
     *
     * @param plainte l'identifiant unique ou le numéro de dossier de la plainte pour laquelle récupérer les jugements, ou null si ce critère est ignoré.
     * @param lowerBound la borne inférieure de date à utiliser pour le filtrage des jugements, ou null si ce critère est ignoré.
     * @param upperBound la borne supérieure de date à utiliser pour le filtrage des jugements, ou null si ce critère est ignoré.
     * @param keyWord le mot-clé à utiliser pour le filtrage des jugements, ou null si ce critère est ignoré.
     * @param decision la décision à utiliser pour le filtrage des jugements, ou null si ce critère est ignoré.
     * @return la spécification pour le filtrage des jugements en fonction des critères de recherche donnés.
     */
    private Specification<Jugement> getSpecification(Plainte plainte, LocalDate lowerBound, LocalDate upperBound, String keyWord, String decision) {
        Specification<Jugement> spec = Specification.where(null);
        if (plainte != null) {
            spec = spec.and(JugementSpecification.getByPlainte(plainte));
        }
        if (lowerBound != null) {
            spec = spec.and(JugementSpecification.getByDateLowerBound(lowerBound));
        }
        if (upperBound != null) {
            spec = spec.and(JugementSpecification.getByDateUpperBound(upperBound));
        }
        if (decision != null && !keyWord.isBlank()) {
            spec = spec.and(JugementSpecification.getByKeyword(keyWord));
        }

        if (decision != null && !decision.isBlank()) {
            spec = spec.and(JugementSpecification.getByDecision(JugementDecision.valueOf(decision)));
        }
        return spec;
    }
}
