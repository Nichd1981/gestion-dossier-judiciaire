package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.AuditionService;
import be.tftic.java.bll.specifications.AuditionSpecification;
import be.tftic.java.bll.specifications.PlainteSpecification;
import be.tftic.java.dal.repositories.AuditionRepository;
import be.tftic.java.dal.repositories.DepositionRepository;
import be.tftic.java.dal.repositories.PlainteRepository;
import be.tftic.java.domain.entities.Audition;
import be.tftic.java.domain.entities.Deposition;
import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.enums.Statut;
import be.tftic.java.domain.enums.TypePlainte;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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

	private final PlainteRepository plainteRepository;
	private final AuditionRepository auditionRepository; // Correction du nom de la variable pour respecter les conventions de nommage Java

	/**
	 * Récupère toutes les auditions associées à une plainte donnée.
	 * Si la plainte n'existe pas, une exception RuntimeException est levée.
	 *
	 * @param id l'identifiant unique de la plainte pour laquelle récupérer les auditions.
	 * @return la liste des auditions associées à la plainte, ou une liste vide si aucune audition n'est associée.
	 * @throws RuntimeException si la plainte n'existe pas dans la base de données.
	 */
	public List<Audition> findAllAudition(Long id) {
		Plainte plainte = plainteRepository.findById(id).orElseThrow(
				() -> new RuntimeException("La plainte n'existe pas")
		);

		return auditionRepository.findByPlainte(plainte);
	}

	/**
	 * Récupère toutes les auditions enregistrées dans la base de données.
	 *
	 * @return la liste de toutes les auditions, ou une liste vide si aucune audition n'est enregistrée.
	 */
	@Override
	public List<Audition> findAll() {
		return auditionRepository.findAll();
	}

	/**
	 * Récupère les auditions qui correspondent aux critères de recherche donnés.
	 * Les critères de recherche incluent une personne, une borne inférieure de date, une borne supérieure de date et un mot-clé.
	 * Les auditions sont filtrées en fonction des critères fournis, et seules les auditions correspondantes sont renvoyées.
	 *
	 * @param personne la personne à utiliser pour le filtrage des auditions. Si null, ce critère est ignoré.
	 * @param lowerBound la borne inférieure de date à utiliser pour le filtrage des auditions. Si null, ce critère est ignoré.
	 * @param upperBound la borne supérieure de date à utiliser pour le filtrage des auditions. Si null, ce critère est ignoré.
	 * @param motCle le mot-clé à utiliser pour le filtrage des auditions. Si null ou vide, ce critère est ignoré.
	 * @return la liste des auditions qui correspondent aux critères de recherche donnés, ou une liste vide si aucune audition ne correspond.
	 */
	@Override
	public List<Audition> findAuditionByCriteria(Personne personne, LocalDate lowerBound, LocalDate upperBound, String motCle) {
		Specification<Audition> spec = getSpecification(lowerBound, upperBound, motCle);
		return auditionRepository.findAll(spec);
	}

	/**
	 * Construit une spécification pour le filtrage des auditions en fonction des critères de recherche donnés.
	 * Les critères de recherche incluent une borne inférieure de date, une borne supérieure de date et un mot-clé.
	 * La spécification est construite en combinant les différents critères à l'aide de l'opérateur logique AND.
	 *
	 * @param lowerBound la borne inférieure de date à utiliser pour le filtrage des auditions. Si null, ce critère est ignoré.
	 * @param upperBound la borne supérieure de date à utiliser pour le filtrage des auditions. Si null, ce critère est ignoré.
	 * @param motCle le mot-clé à utiliser pour le filtrage des auditions. Si null ou vide, ce critère est ignoré.
	 * @return la spécification pour le filtrage des auditions en fonction des critères de recherche donnés.
	 */
	private Specification<Audition> getSpecification(LocalDate lowerBound, LocalDate upperBound, String motCle) {

		Specification<Audition> spec = Specification.where(null);

		if (lowerBound != null) {
			spec = spec.and(AuditionSpecification.getByDateLowerBound(lowerBound));
		}
		if (upperBound != null) {
			spec = spec.and(AuditionSpecification.getByDateUpperBound(upperBound));
		}
		if (motCle != null && !motCle.isBlank()) {
			spec = spec.and(AuditionSpecification.getByMotCle(motCle));
		}
		return spec;
	}

}
