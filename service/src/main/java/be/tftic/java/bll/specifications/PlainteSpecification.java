package be.tftic.java.bll.specifications;

import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.enums.Statut;
import be.tftic.java.domain.enums.TypePlainte;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

/**
 * Classe utilitaire pour créer des spécifications JPA pour les entités Plainte.
 *
 * Fournit des méthodes statiques pour créer des spécifications basées sur des critères de recherche
 * tels que le numéro de dossier, des bornes de dates, le statut, le plaignant et le type de plainte.
 */
public class PlainteSpecification {

    /**
     * Crée une spécification pour rechercher des plaintes contenant un numéro de dossier spécifique.
     *
     * @param numeroDossier le numéro de dossier à rechercher
     * @return une spécification JPA pour rechercher les plaintes par numéro de dossier
     */
    public static Specification<Plainte> getByNumeroDossier(String numeroDossier) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("numeroDossier"), "%" + numeroDossier + "%"));
    }

    /**
     * Crée une spécification pour rechercher des plaintes dont la date est postérieure ou égale à une date donnée.
     *
     * @param lowerBound la date de début de la période de recherche
     * @return une spécification JPA pour rechercher les plaintes par date de début
     */
    public static Specification<Plainte> getByDateLowerBound(LocalDate lowerBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("datePlainte"), lowerBound));
    }

    /**
     * Crée une spécification pour rechercher des plaintes dont la date est antérieure ou égale à une date donnée.
     *
     * @param upperBound la date de fin de la période de recherche
     * @return une spécification JPA pour rechercher les plaintes par date de fin
     */
    public static Specification<Plainte> getByDateUpperBound(LocalDate upperBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("datePlainte"), upperBound));
    }

    /**
     * Crée une spécification pour rechercher des plaintes par un statut spécifique.
     *
     * @param statut le statut de la plainte à rechercher
     * @return une spécification JPA pour rechercher les plaintes par statut
     */
    public static Specification<Plainte> getByStatut(Statut statut) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("statut"), statut));
    }
    /**
     * Crée une spécification pour rechercher des plaintes associées à un plaignant spécifique.
     *
     * @param plaignant l'entité Personne plaignant
     * @return une spécification JPA pour rechercher les plaintes par plaignant
     */
    public static Specification<Plainte> getByPlaignant(Personne plaignant) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("plaignant"), plaignant));
    }

    /**
     * Crée une spécification pour rechercher des plaintes par un type spécifique.
     *
     * @param type le type de la plainte à rechercher
     * @return une spécification JPA pour rechercher les plaintes par type
     */
    public static Specification<Plainte> getByType(TypePlainte type) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type));
    }

}


/**
 * Crée une spécification pour rechercher des plaintes associées à un plaignant spécifique.
 *
 * @param plaignant l'entité Personne plaignant
 * @return une spécification JPA pour rechercher les plaintes par plaign

