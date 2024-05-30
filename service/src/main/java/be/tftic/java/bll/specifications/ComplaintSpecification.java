package be.tftic.java.bll.specifications;

import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.entities.Complaint;
import be.tftic.java.domain.enums.ComplaintStatus;
import be.tftic.java.domain.enums.ComplaintType;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

/**
 * Classe de spécifications pour la recherche de plaintes.
 * Cette classe fournit des méthodes statiques pour créer des spécifications de recherche de plaintes en fonction de différents critères.
 *
 */
public class ComplaintSpecification {

    /**
     * Méthode de spécification pour la recherche de plaintes en fonction d'un numéro de dossier.
     *
     * @param fileNumber le numéro de dossier pour la recherche de plaintes
     * @return la spécification de recherche de plaintes en fonction d'un numéro de dossier
     */
    public static Specification<Complaint> getByFileNumber(String fileNumber) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("fileNumber"), "%" + fileNumber + "%"));
    }

    /**
     * Méthode de spécification pour la recherche de plaintes en fonction d'une date minimale.
     *
     * @param lowerBound la date minimale pour la recherche de plaintes
     * @return la spécification de recherche de plaintes en fonction d'une date minimale
     */
    public static Specification<Complaint> getByDateLowerBound(LocalDate lowerBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("complaintDate"), lowerBound));
    }

    /**
     * Méthode de spécification pour la recherche de plaintes en fonction d'une date maximale.
     *
     * @param upperBound la date maximale pour la recherche de plaintes
     * @return la spécification de recherche de plaintes en fonction d'une date maximale
     */
    public static Specification<Complaint> getByDateUpperBound(LocalDate upperBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("complaintDate"), upperBound));
    }

    /**
     * Méthode de spécification pour la recherche de plaintes en fonction d'un statut.
     *
     * @param status le statut pour la recherche de plaintes
     * @return la spécification de recherche de plaintes en fonction d'un statut
     */
    public static Specification<Complaint> getByStatus(ComplaintStatus status) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status));
    }

    /**
     * Méthode de spécification pour la recherche de plaintes en fonction d'un plaignant.
     *
     * @param complainant le plaignant pour la recherche de plaintes
     * @return la spécification de recherche de plaintes en fonction d'un plaignant
     */
    public static Specification<Complaint> getByComplainant(Person complainant){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("complainant"), complainant));
    }

    /**
     * Méthode de spécification pour la recherche de plaintes en fonction d'un type.
     *
     * @param type le type pour la recherche de plaintes
     * @return la spécification de recherche de plaintes en fonction d'un type
     */
    public static Specification<Complaint> getByType(ComplaintType type){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type));
    }

}
