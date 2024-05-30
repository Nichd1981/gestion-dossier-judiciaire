package be.tftic.java.bll.specifications;

import be.tftic.java.domain.entities.Judgment;
import be.tftic.java.domain.entities.Complaint;
import be.tftic.java.domain.enums.JudgmentDecision;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

/**
 * Classe de spécifications pour la recherche de jugements.
 * Cette classe fournit des méthodes statiques pour créer des spécifications de recherche de jugements en fonction de différents critères.
 *
 */
public class JudgmentSpecification {

    /**
     * Méthode de spécification pour la recherche de jugements en fonction d'une plainte.
     *
     * @param complaint la plainte pour la recherche de jugements
     * @return la spécification de recherche de jugements en fonction d'une plainte
     */
    public static Specification<Judgment> getByComplaint(Complaint complaint){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("complaint"), complaint));
    }

    /**
     * Méthode de spécification pour la recherche de jugements en fonction d'une date minimale.
     *
     * @param lowerBound la date minimale pour la recherche de jugements
     * @return la spécification de recherche de jugements en fonction d'une date minimale
     */
    public static Specification<Judgment> getByDateLowerBound(LocalDate lowerBound){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("judgmentDate"), lowerBound));
    }

    /**
     * Méthode de spécification pour la recherche de jugements en fonction d'une date maximale.
     *
     * @param upperBound la date maximale pour la recherche de jugements
     * @return la spécification de recherche de jugements en fonction d'une date maximale
     */
    public static Specification<Judgment> getByDateUpperBound(LocalDate upperBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("judgmentDate"), upperBound));
    }

    /**
     * Méthode de spécification pour la recherche de jugements en fonction d'un mot-clé.
     *
     * @param keyword le mot-clé pour la recherche de jugements
     * @return la spécification de recherche de jugements en fonction d'un mot-clé
     */
    public static Specification<Judgment> getByKeyword(String keyword) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("commentary"), "%" + keyword + "%"));
    }

    /**
     * Méthode de spécification pour la recherche de jugements en fonction d'une décision.
     *
     * @param judgmentDecision la décision pour la recherche de jugements
     * @return la spécification de recherche de jugements en fonction d'une décision
     */
    public static Specification<Judgment> getByDecision(JudgmentDecision judgmentDecision) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("judgmentDecision"), judgmentDecision));
    }

}
