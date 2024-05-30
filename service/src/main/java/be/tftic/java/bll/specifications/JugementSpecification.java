package be.tftic.java.bll.specifications;

import be.tftic.java.domain.entities.Jugement;
import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.enums.JugementDecision;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

/**
 * Classe de spécifications pour la recherche de jugements.
 * Cette classe fournit des méthodes statiques pour créer des spécifications de recherche de jugements en fonction de différents critères.
 *
 */
public class JugementSpecification {

    /**
     * Méthode de spécification pour la recherche de jugements en fonction d'une plainte.
     *
     * @param p la plainte pour la recherche de jugements
     * @return la spécification de recherche de jugements en fonction d'une plainte
     */
    public static Specification<Jugement> getByPlainte(Plainte p){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("plainte"), p));
    }

    /**
     * Méthode de spécification pour la recherche de jugements en fonction d'une date minimale.
     *
     * @param lowerBound la date minimale pour la recherche de jugements
     * @return la spécification de recherche de jugements en fonction d'une date minimale
     */
    public static Specification<Jugement> getByDateLowerBound(LocalDate lowerBound){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateJugement"), lowerBound));
    }

    /**
     * Méthode de spécification pour la recherche de jugements en fonction d'une date maximale.
     *
     * @param upperBound la date maximale pour la recherche de jugements
     * @return la spécification de recherche de jugements en fonction d'une date maximale
     */
    public static Specification<Jugement> getByDateUpperBound(LocalDate upperBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dateJugement"), upperBound));
    }

    /**
     * Méthode de spécification pour la recherche de jugements en fonction d'un mot-clé.
     *
     * @param keyword le mot-clé pour la recherche de jugements
     * @return la spécification de recherche de jugements en fonction d'un mot-clé
     */
    public static Specification<Jugement> getByKeyword(String keyword) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("commentaire"), "%" + keyword + "%"));
    }

    /**
     * Méthode de spécification pour la recherche de jugements en fonction d'une décision.
     *
     * @param decision la décision pour la recherche de jugements
     * @return la spécification de recherche de jugements en fonction d'une décision
     */
    public static Specification<Jugement> getByDecision(JugementDecision decision) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("jugementDecision"), decision));
    }

}
