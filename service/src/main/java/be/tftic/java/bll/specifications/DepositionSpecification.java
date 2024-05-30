package be.tftic.java.bll.specifications;

import be.tftic.java.domain.entities.Deposition;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

/**
 * Classe de spécifications pour la recherche de dépositions.
 * Cette classe fournit des méthodes statiques pour créer des spécifications de recherche de dépositions en fonction de différents critères.
 *
 */
public class DepositionSpecification {

    /**
     * Méthode de spécification pour la recherche de dépositions en fonction d'une date minimale.
     *
     * @param lowerBound la date minimale pour la recherche de dépositions
     * @return la spécification de recherche de dépositions en fonction d'une date minimale
     */
    public static Specification<Deposition> getByDateLowerBound(LocalDate lowerBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateDeposition"), lowerBound));
    }

    /**
     * Méthode de spécification pour la recherche de dépositions en fonction d'une date maximale.
     *
     * @param upperBound la date maximale pour la recherche de dépositions
     * @return la spécification de recherche de dépositions en fonction d'une date maximale
     */
    public static Specification<Deposition> getByDateUpperBound(LocalDate upperBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateDeposition"), upperBound));
    }

    /**
     * Méthode de spécification pour la recherche de dépositions en fonction d'un mot-clé.
     * La recherche est effectuée en ignorant la casse.
     *
     * @param keyword le mot-clé pour la recherche de dépositions
     * @return la spécification de recherche de dépositions en fonction d'un mot-clé
     */
    public static Specification<Deposition> getByKeyword(String keyword) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("deposition")), "%" + keyword.toLowerCase() + "%"));
    }

}
