package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.exceptions.EntityNotFoundException;
import be.tftic.java.bll.services.AuditionService;
import be.tftic.java.bll.services.PersonService;
import be.tftic.java.bll.specifications.AuditionSpecification;
import be.tftic.java.common.models.requests.create.AuditionCreateRequest;
import be.tftic.java.common.models.requests.filter.AuditionFilterRequest;
import be.tftic.java.common.models.responses.AuditionShortResponse;
import be.tftic.java.dal.repositories.AuditionRepository;
import be.tftic.java.dal.repositories.ComplaintRepository;
import be.tftic.java.domain.entities.Audition;
import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.entities.Complaint;
import be.tftic.java.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe de service pour la gestion des opérations liées à l'entité Audition.
 * Cette classe fournit une couche d'abstraction entre la couche de contrôleur et la couche de persistance,
 * permettant de gérer les opérations métier et de maintenir une séparation des préoccupations.
 *
 * @Service indique que cette classe est un composant Spring géré par le conteneur d'injection de dépendances.
 * Spring s'occupe de créer une instance unique de cette classe et de la fournir là où elle est nécessaire.
 * @RequiredArgsConstructor indique que le constructeur généré par Lombok ne prend en compte que les attributs finaux.
 * Dans ce cas, cela signifie que le constructeur injecte les instances de PlainteRepository et AuditionRepository fournies par Spring.
 * AuditionService indique que cette classe implémente l'interface AuditionService,
 * ce qui permet de garantir que les méthodes nécessaires sont fournies et facilite le remplacement ou l'extension de l'implémentation.
 */
@Service
@RequiredArgsConstructor
public class AuditionServiceImpl implements AuditionService {

	private final ComplaintRepository complaintRepository;
	private final AuditionRepository auditionRepository;
	private final PersonService personService;

	@Override
	public void create(AuditionCreateRequest request) {
		Audition audition = request.toEntity();

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Person agent = user.getPerson();
		audition.setAgent(agent);
		Person citizen = personService.findById(request.citizenId());
		audition.setCitizen(citizen);
		if (request.lawyerId() != null){
			Person lawyer = personService.findById(request.lawyerId());
			audition.setLawyer(lawyer);
		}

		Complaint complaint = getComplaint(request.complaintId());
		audition.setComplaint(complaint);
		auditionRepository.save(audition);
	}

	/**
	 * Récupère toutes les auditions associées à une plainte donnée.
	 * Si la plainte n'existe pas, une exception RuntimeException est levée.
	 *
	 * @param id l'identifiant unique de la plainte pour laquelle récupérer les auditions.
	 * @return la liste des auditions associées à la plainte, ou une liste vide si aucune audition n'est associée.
	 * @throws RuntimeException si la plainte n'existe pas dans la base de données.
	 */
    @Override
	public List<AuditionShortResponse> findAllAudition(Long id) {
		Complaint complaint = getComplaint(id);

		return auditionRepository.findByComplaint(complaint)
				.stream()
				.map(AuditionShortResponse::fromEntity)
				.toList();
	}

	/**
	 * Récupère toutes les auditions enregistrées dans la base de données.
	 *
	 * @return la liste de toutes les auditions, ou une liste vide si aucune audition n'est enregistrée.
	 */
	@Override
	public List<AuditionShortResponse> findAll() {
		return auditionRepository.findAll()
				.stream()
				.map(AuditionShortResponse::fromEntity)
				.toList();
	}

	/**
	 * Récupère les auditions qui correspondent aux critères de recherche donnés.
	 * Les critères de recherche incluent une personne, une borne inférieure de date, une borne supérieure de date et un mot-clé.
	 * Les auditions sont filtrées en fonction des critères fournis, et seules les auditions correspondantes sont renvoyées.
	 *
	 * @return la liste des auditions qui correspondent aux critères de recherche donnés, ou une liste vide si aucune audition ne correspond.
	 */
	@Override
	public List<AuditionShortResponse> findAuditionByCriteria(AuditionFilterRequest f) {
		Specification<Audition> spec = getSpecification(f.getDateLowerBound(), f.getDateUpperBound(), f.getKeyword());
		return auditionRepository.findAll(spec)
				.stream()
				.map(AuditionShortResponse::fromEntity)
				.toList();
	}

    private Complaint getComplaint(Long complaintId){
		return complaintRepository.findById(complaintId).orElseThrow(
				() -> new EntityNotFoundException("Complaint not found")
		);
    }

	/**
	 * Construit une spécification pour le filtrage des auditions en fonction des critères de recherche donnés.
	 * Les critères de recherche incluent une borne inférieure de date, une borne supérieure de date et un mot-clé.
	 * La spécification est construite en combinant les différents critères à l'aide de l'opérateur logique AND.
	 *
	 * @param lowerBound la borne inférieure de date à utiliser pour le filtrage des auditions. Si null, ce critère est ignoré.
	 * @param upperBound la borne supérieure de date à utiliser pour le filtrage des auditions. Si null, ce critère est ignoré.
	 * @param keyword le mot-clé à utiliser pour le filtrage des auditions. Si null ou vide, ce critère est ignoré.
	 * @return la spécification pour le filtrage des auditions en fonction des critères de recherche donnés.
	 */
		private Specification<Audition> getSpecification(LocalDate lowerBound, LocalDate upperBound, String keyword) {
		Specification<Audition> spec = Specification.where(null);

		if (lowerBound != null) {
			spec = spec.and(AuditionSpecification.getByDateLowerBound(lowerBound));
		}
		if (upperBound != null) {
			spec = spec.and(AuditionSpecification.getByDateUpperBound(upperBound));
		}
		if(keyword != null && !keyword.isBlank()) {
			spec = spec.and(AuditionSpecification.getByKeyword(keyword));
		}
		return spec;
	}

}
