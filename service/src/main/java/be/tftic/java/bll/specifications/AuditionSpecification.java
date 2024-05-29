package be.tftic.java.bll.specifications;

import be.tftic.java.domain.entities.Audition;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

/**
 * Classe utilitaire pour créer des spécifications JPA pour les entités Audition.
 *
 * Fournit des méthodes statiques pour créer des spécifications basées sur des critères de recherche
 * tels que des bornes de dates et des mots-clés.
 */
public class AuditionSpecification {

	/**
	 * Crée une spécification pour rechercher des auditions dont la date est postérieure ou égale à une date donnée.
	 *
	 * @param lowerBound la date de début de la période de recherche
	 * @return une spécification JPA pour rechercher les auditions par date de début
	 */
	public static Specification<Audition> getByDateLowerBound(LocalDate lowerBound) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateAudition"), lowerBound);
	}

	/**
	 * Crée une spécification pour rechercher des auditions dont la date est antérieure ou égale à une date donnée.
	 *
	 * @param upperBound la date de fin de la période de recherche
	 * @return une spécification JPA pour rechercher les auditions par date de fin
	 */
	public static Specification<Audition> getByDateUpperBound(LocalDate upperBound) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dateAudition"), upperBound);
	}

	/**
	 * Crée une spécification pour rechercher des auditions contenant un mot-clé spécifique dans la déposition.
	 *
	 * @param motCle le mot-clé à rechercher dans la déposition
	 * @return une spécification JPA pour rechercher les auditions par mot-clé
	 */
	public static Specification<Audition> getByMotCle(String motCle) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("depositionAudition"), "%" + motCle + "%");
	}

}

