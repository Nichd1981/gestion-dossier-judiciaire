package be.tftic.java.bll.specifications;

import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.enums.Statut;
import be.tftic.java.domain.enums.TypePlainte;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

/**
 * Classe de spécifications pour la recherche de plaintes.
 * Cette classe fournit des méthodes statiques pour créer des spécifications de recherche de plaintes en fonction de différents critères.
 *
 */
public class PlainteSpecification {

    /**
     * Méthode de spécification pour la recherche de plaintes en fonction d'un numéro de dossier.
     *
     * @param numeroDossier le numéro de dossier pour la recherche de plaintes
     * @return la spécification de recherche de plaintes en fonction d'un numéro de dossier
     */
    public static Specification<Plainte> getByNumeroDossier(String numeroDossier) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("numeroDossier"), "%" + numeroDossier + "%"));
    }

    /**
     * Méthode de spécification pour la recherche de plaintes en fonction d'une date minimale.
     *
     * @param lowerBound la date minimale pour la recherche de plaintes
     * @return la spécification de recherche de plaintes en fonction d'une date minimale
     */
    public static Specification<Plainte> getByDateLowerBound(LocalDate lowerBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("datePlainte"), lowerBound));
    }

    /**
     * Méthode de spécification pour la recherche de plaintes en fonction d'une date maximale.
     *
     * @param upperBound la date maximale pour la recherche de plaintes
     * @return la spécification de recherche de plaintes en fonction d'une date maximale
     */
    public static Specification<Plainte> getByDateUpperBound(LocalDate upperBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("datePlainte"), upperBound));
    }

    /**
     * Méthode de spécification pour la recherche de plaintes en fonction d'un statut.
     *
     * @param statut le statut pour la recherche de plaintes
     * @return la spécification de recherche de plaintes en fonction d'un statut
     */
    public static Specification<Plainte> getByStatut(Statut statut) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("statut"), statut));
    }

    /**
     * Méthode de spécification pour la recherche de plaintes en fonction d'un plaignant.
     *
     * @param plaignant le plaignant pour la recherche de plaintes
     * @return la spécification de recherche de plaintes en fonction d'un plaignant
     */
    public static Specification<Plainte> getByPlaignant(Personne plaignant){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("plaignant"), plaignant));
    }

    /**
     * Méthode de spécification pour la recherche de plaintes en fonction d'un type.
     *
     * @param type le type pour la recherche de plaintes
     * @return la spécification de recherche de plaintes en fonction d'un type
     */
    public static Specification<Plainte> getByType(TypePlainte type){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type));
    }

}
