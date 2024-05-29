package be.tftic.java.bll.specifications;

import be.tftic.java.domain.entities.Jugement;
import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.enums.JugementDecision;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

/**
 * Classe utilitaire pour créer des spécifications JPA pour les entités Jugement.
 *
 * Fournit des méthodes statiques pour créer des spécifications basées sur des critères de recherche
 * tels que la plainte associée, des bornes de dates, des mots-clés et des décisions de jugement.
 */
public class JugementSpecification {

    /**
     * Crée une spécification pour rechercher des jugements associés à une plainte spécifique.
     *
     * @param p l'entité Plainte associée aux jugements
     * @return une spécification JPA pour rechercher les jugements par plainte
     */
    public static Specification<Jugement> getByPlainte(Plainte p) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("plainte"), p);
    }

    /**
     * Crée une spécification pour rechercher des jugements dont la date est postérieure ou égale à une date donnée.
     *
     * @param lowerBound la date de début de la période de recherche
     * @return une spécification JPA pour rechercher les jugements par date de début
     */
    public static Specification<Jugement> getByDateLowerBound(LocalDate lowerBound) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateJugement"), lowerBound);
    }

    /**
     * Crée une spécification pour rechercher des jugements dont la date est antérieure ou égale à une date donnée.
     *
     * @param upperBound la date de fin de la période de recherche
     * @return une spécification JPA pour rechercher les jugements par date de fin
     */
    public static Specification<Jugement> getByDateUpperBound(LocalDate upperBound) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dateJugement"), upperBound);
    }

    /**
     * Crée une spécification pour rechercher des jugements contenant un mot-clé spécifique dans les commentaires.
     *
     * @param keyword le mot-clé à rechercher dans les commentaires
     * @return une spécification JPA pour rechercher les jugements par mot-clé
     */
    public static Specification<Jugement> getByKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("commentaire"), "%" + keyword + "%");
    }

    /**
     * Crée une spécification pour rechercher des jugements par une décision spécifique.
     *
     * @param decision la décision de jugement
     * @return une spécification JPA pour rechercher les jugements par décision
     */
    public static Specification<Jugement> getByDecision(JugementDecision decision) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("jugementDecision"), decision);
    }

}

