package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.exceptions.EntityNotFoundException;
import be.tftic.java.bll.exceptions.complaint.CloseComplaintException;
import be.tftic.java.bll.services.JudgmentService;
import be.tftic.java.bll.services.PersonService;
import be.tftic.java.bll.services.ComplaintService;
import be.tftic.java.bll.specifications.ComplaintSpecification;
import be.tftic.java.common.models.requests.filter.ComplaintFilterRequest;
import be.tftic.java.common.models.requests.update.ClosedSurveyRequest;
import be.tftic.java.common.models.requests.create.ComplaintCreateRequest;
import be.tftic.java.common.models.responses.ComplaintDetailResponse;
import be.tftic.java.common.models.responses.ComplaintShortResponse;
import be.tftic.java.dal.repositories.ComplaintRepository;
import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.entities.Complaint;
import be.tftic.java.domain.entities.User;
import be.tftic.java.domain.enums.ComplaintStatus;
import be.tftic.java.domain.enums.ComplaintType;
import be.tftic.java.domain.enums.Gender;
import be.tftic.java.il.utils.MailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Classe de service pour la gestion des opérations liées à l'entité Plainte.
 * Cette classe fournit une couche d'abstraction entre la couche de contrôleur et la couche de persistance,
 * permettant de gérer les opérations métier et de maintenir une séparation des préoccupations.
 *
 * @Service indique que cette classe est un composant Spring géré par le conteneur d'injection de dépendances.
 * Spring s'occupe de créer une instance unique de cette classe et de la fournir là où elle est nécessaire.
 * @RequiredArgsConstructor indique que le constructeur généré par Lombok ne prend en compte que les attributs finaux.
 * Dans ce cas, cela signifie que le constructeur injecte les instances de PlainteRepository, PersonneService et JugementService fournies par Spring.
 * PlainteService indique que cette classe implémente l'interface PlainteService,
 * ce qui permet de garantir que les méthodes nécessaires sont fournies et facilite le remplacement ou l'extension de l'implémentation.
 */
@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final PersonService personService;
    private final JudgmentService judgmentService;
    private final MailUtils mailUtils;

    /**
     * Récupère toutes les plaintes déposées par une personne donnée.
     *
     * @return la liste des plaintes déposées par la personne, ou une liste vide si aucune plainte n'a été déposée.
     */
    @Override
    public List<ComplaintShortResponse> findByComplainantId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return complaintRepository.findByComplainantId(user.getPerson().getId())
                .stream()
                .map(ComplaintShortResponse::fromEntity)
                .toList();
    }

    /**
     * Récupère toutes les plaintes dans lesquelles une personne donnée est impliquée.
     *
     * @return la liste des plaintes dans lesquelles la personne est impliquée, ou une liste vide si aucune plainte ne concerne la personne.
     */
    @Override
    public List<ComplaintShortResponse> findByPersonConcerned() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return complaintRepository.findByPersonConcerned(user.getPerson())
                .stream()
                .map(ComplaintShortResponse::fromEntity)
                .toList();
    }

    /**
     * Récupère toutes les plaintes enregistrées dans la base de données.
     *
     * @return la liste de toutes les plaintes, ou une liste vide si aucune plainte n'est enregistrée.
     */
    @Override
    public List<ComplaintShortResponse> findAll() {
        return complaintRepository.findAll()
                .stream()
                .map(ComplaintShortResponse::fromEntity)
                .toList();
    }

    /**
     * Récupère une plainte donnée à partir de son identifiant unique.
     * Si la plainte n'existe pas, une exception RuntimeException est levée.
     *
     * @param id l'identifiant unique de la plainte à récupérer.
     * @return la plainte correspondant à l'identifiant unique donné.
     * @throws RuntimeException si la plainte n'existe pas.
     */
    @Override
    public ComplaintDetailResponse findById(Long id) {
        return ComplaintDetailResponse.fromEntity(getComplaint(id));
    }

    /**
     * Récupère une plainte donnée à partir de son numéro de dossier.
     * Si la plainte n'existe pas, une exception RuntimeException est levée.
     *
     * @param fileNumber le numéro de dossier de la plainte à récupérer.
     * @return la plainte correspondant au numéro de dossier donné.
     * @throws RuntimeException si la plainte n'existe pas.
     */
    @Override
    public ComplaintDetailResponse findByFileNumber(String fileNumber) {
        return ComplaintDetailResponse.fromEntity(getComplaint(fileNumber));
    }

    /**
     * Récupère les plaintes qui correspondent aux critères de recherche donnés.
     * Les critères de recherche incluent un numéro de dossier, une borne inférieure de date, une borne supérieure de date et un statut.
     * Les plaintes sont filtrées en fonction des critères fournis, et seules les plaintes correspondantes sont renvoyées.
     *
     * @return la liste des plaintes qui correspondent aux critères de recherche donnés, ou une liste vide si aucune plainte ne correspond.
     */
    @Override
    public List<ComplaintShortResponse> findByCriteria(ComplaintFilterRequest f) {
        Specification<Complaint> spec = getSpecification(f.getFileNumber(), f.getDateLowerBound(), f.getDateUpperBound(), f.getStatus(), null, null);

        return complaintRepository.findAll(spec)
                .stream()
                .map(ComplaintShortResponse::fromEntity)
                .toList();
    }

    /**
     * Crée une nouvelle plainte à partir des informations fournies dans le formulaire.
     *
     * @param form le formulaire contenant les informations de la plainte, y compris l'identifiant unique du plaignant, l'identifiant unique de l'agent traitant et les identifiants uniques des personnes concernées.
     * @return la plainte nouvellement créée.
     */
    @Override
    public Complaint create(ComplaintCreateRequest form) {

        Complaint plainte = form.toEntity();
        plainte.setComplainant(personService.findById(form.idComplainant()));
        plainte.setAgent(personService.findById(form.idAgent()));
        form.idConcerned().forEach(id -> {
            Person person = personService.findById(id);
            plainte.getPersonConcerned().add(person);
        });

        return complaintRepository.save(plainte);
    }

    /**
     * Met à jour le statut d'une plainte donnée à "En cours".
     *
     * @param id l'identifiant unique de la plainte à mettre à jour.
     */
    @Override
    public void openSurvey(Long id) {
        Complaint toUpdate = getComplaint(id);

        toUpdate.setStatus(ComplaintStatus.IN_PROGRESS);
        complaintRepository.save(toUpdate);
    }

    /**
     * Met à jour le statut d'une plainte donnée à "Clôturée" et crée un jugement associé si le type de plainte est Délit ou Crime.
     * Si la plainte n'est pas déjà "En cours", une exception RuntimeException est levée.
     *
     * @param form le formulaire contenant les informations de clôture de la plainte, y compris l'identifiant unique de la plainte et le type de plainte.
     * @throws RuntimeException si la plainte n'est pas déjà "En cours".
     */
    @Override
    public void closedSurvey(ClosedSurveyRequest form) {
        Complaint toUpdate = getComplaint(form.complaintId());

        // Si la plainte n'est pas encore ouverte, exception!
        if (toUpdate.getStatus() != ComplaintStatus.IN_PROGRESS) {
            throw new CloseComplaintException();
        }

        toUpdate.setStatus(ComplaintStatus.CLOSED);
        toUpdate.setTypeComplaint(ComplaintType.valueOf(form.type()));
        // Si le type de plainte est Délit ou Crime, créer un Jugement associé
        if (ComplaintType.valueOf(form.type()) == ComplaintType.OFFENSE || ComplaintType.valueOf(form.type()) == ComplaintType.CRIME) {
            judgmentService.create(form.complaintId());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateFormatted = toUpdate.getComplaintDate().format(formatter);



        Context context = new Context();
        context.setVariable("complainantName", toUpdate.getComplainant().getName());
        context.setVariable("gender", toUpdate.getComplainant().getGender().equals(Gender.MALE) ? "Mr" : "Mme");
        context.setVariable("fileNumber", toUpdate.getFileNumber());
        context.setVariable("complaintDate", dateFormatted);
        context.setVariable("complaintStatus", toUpdate.getStatus());
        context.setVariable("agent", toUpdate.getAgent().getName());
        mailUtils.sendEmail("a.hassaini@stag.technofuturtic.education", "Complaint", "Clotûre d'enquête", context);
        complaintRepository.save(toUpdate);
    }

    /**
     * Récupère les plaintes déposées par une personne donnée qui correspondent aux critères de recherche donnés.
     * Les critères de recherche incluent un numéro de dossier, une borne inférieure de date, une borne supérieure de date, un statut et un type de plainte.
     * Les plaintes sont filtrées en fonction des critères fournis et de la personne qui les a déposées, et seules les plaintes correspondantes sont renvoyées.
     *
     * @return la liste des plaintes qui correspondent aux critères de recherche donnés et qui ont été déposées par la personne donnée, ou une liste vide si aucune plainte ne correspond.
     */
    @Override
    public List<ComplaintShortResponse> findByComplaintIdWithCriteria(ComplaintFilterRequest f) {
        User complainant = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        Specification <Complaint> spec = getSpecification(f.getFileNumber(),
                                                        f.getDateLowerBound(),
                                                        f.getDateUpperBound(),
                                                        f.getStatus(),
                                                        f.getType(),
                                                        complainant.getPerson());
        return complaintRepository.findAll(spec)
                .stream()
                .map(ComplaintShortResponse::fromEntity)
                .toList();
    }

    public List<ComplaintShortResponse> getComplaintByCustomerAndLawyer(Long customerId) {
        User lawyer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return complaintRepository.findComplaintByCustomersAndLawyer(customerId, lawyer.getPerson().getId())
                .stream()
                .map(ComplaintShortResponse::fromEntity)
                .toList();
    }

    private Complaint getComplaint(Long complaintId){
        return complaintRepository.findById(complaintId).orElseThrow(
                () -> new EntityNotFoundException("Complaint not found")
        );
    }

    private Complaint getComplaint(String fileNumber){
        return complaintRepository.findByFileNumber(fileNumber).orElseThrow(
                () -> new EntityNotFoundException("Complaint not found")
        );
    }

    /**
     * Construit une spécification pour le filtrage des plaintes en fonction des critères de recherche donnés.
     * Les critères de recherche incluent un numéro de dossier, une borne inférieure de date, une borne supérieure de date, un statut, un type de plainte et une personne.
     * La spécification est construite en combinant les différents critères à l'aide de l'opérateur logique AND.
     *
     * @param fileNumber le numéro de dossier de la plainte à récupérer, ou null si ce critère est ignoré.
     * @param lowerBound la borne inférieure de date à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @param upperBound la borne supérieure de date à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @param status le statut à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @param type le type de plainte à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @param complainant la personne pour laquelle récupérer les plaintes, ou null si ce critère est ignoré.
     * @return la spécification pour le filtrage des plaintes en fonction des critères de recherche donnés.
     */
    private Specification<Complaint> getSpecification(String fileNumber, LocalDate lowerBound, LocalDate upperBound, String status, String type, Person complainant) {

        Specification<Complaint> spec = Specification.where(null);
        if(!fileNumber.isBlank()){
            spec = spec.and(ComplaintSpecification.getByFileNumber(fileNumber));
        }
        if(lowerBound != null){
            spec = spec.and(ComplaintSpecification.getByDateLowerBound(lowerBound));
        }
        if(upperBound != null){
            spec = spec.and(ComplaintSpecification.getByDateUpperBound(upperBound));
        }
        if(status != null && !status.isBlank()){
            spec = spec.and(ComplaintSpecification.getByStatus(ComplaintStatus.valueOf(status)));
        }
        if(type != null && !type.isBlank()){
            spec = spec.and(ComplaintSpecification.getByType(ComplaintType.valueOf(type)));
        }
        if(complainant != null){
            spec = spec.and(ComplaintSpecification.getByComplainant(complainant));
        }
        return spec;
    }

}
