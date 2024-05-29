package be.tftic.java.bll.specifications;

import be.tftic.java.domain.entities.Deposition;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

/**
 * Classe utilitaire pour créer des spécifications JPA pour les entités Deposition.
 *
 * Fournit des méthodes statiques pour créer des spécifications basées sur des critères de recherche
 * tels que des bornes de dates et des mots-clés.
 */
public class DepositionSpecification {

    /**
     * Crée une spécification pour rechercher des dépositions dont la date est postérieure ou égale à une date donnée.
     *
     * @param lowerBound la date de début de la période de recherche
     * @return une spécification JPA pour rechercher les dépositions par date de début
     */
    public static Specification<Deposition> getByDateLowerBound(LocalDate lowerBound) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateDeposition"), lowerBound);
    }

    /**
     * Crée une spécification pour rechercher des dépositions dont la date est antérieure ou égale à une date donnée.
     *
     * @param upperBound la date de fin de la période de recherche
     * @return une spécification JPA pour rechercher les dépositions par date de fin
     */
    public static Specification<Deposition> getByDateUpperBound(LocalDate upperBound) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dateDeposition"), upperBound);
    }

    /**
     * Crée une spécification pour rechercher des dépositions contenant un mot-clé spécifique dans la déposition.
     *
     * @param keyword le mot-clé à rechercher dans la déposition
     * @return une spécification JPA pour rechercher les dépositions par mot-clé
     */
    public static Specification<Deposition> getByKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("deposition")), "%" + keyword.toLowerCase() + "%");
    }
}

