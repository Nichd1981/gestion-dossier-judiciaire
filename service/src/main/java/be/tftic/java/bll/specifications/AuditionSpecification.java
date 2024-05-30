package be.tftic.java.bll.specifications;

import be.tftic.java.domain.entities.Audition;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

/**
 * Classe de spécifications pour la recherche d'auditions.
 * Cette classe fournit des méthodes statiques pour créer des spécifications de recherche d'auditions en fonction de différents critères.
 *
 */
public class AuditionSpecification {

	/**
	 * Méthode de spécification pour la recherche d'auditions en fonction d'une date minimale.
	 *
	 * @param lowerBound la date minimale pour la recherche d'auditions
	 * @return la spécification de recherche d'auditions en fonction d'une date minimale
	 */
	public static Specification<Audition> getByDateLowerBound(LocalDate lowerBound) {
		return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateAudition"), lowerBound));
	}

	/**
	 * Méthode de spécification pour la recherche d'auditions en fonction d'une date maximale.
	 *
	 * @param upperBound la date maximale pour la recherche d'auditions
	 * @return la spécification de recherche d'auditions en fonction d'une date maximale
	 */
	public static Specification<Audition> getByDateUpperBound(LocalDate upperBound) {
		return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dateAudition"), upperBound));
	}

	/**
	 * Méthode de spécification pour la recherche d'auditions en fonction d'un mot-clé.
	 *
	 * @param keyword le mot-clé pour la recherche d'auditions
	 * @return la spécification de recherche d'auditions en fonction d'un mot-clé
	 */
	public static Specification<Audition> getByKeyword(String keyword) {
		return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("depositionAudition"), "%" + keyword + "%"));
	}

}
