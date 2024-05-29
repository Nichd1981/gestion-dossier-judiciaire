package be.tftic.java.bll.services;

import be.tftic.java.common.models.requests.DepositionFilterRequest;
import be.tftic.java.common.models.responses.DepositionShortResponse;
import be.tftic.java.domain.entities.Deposition;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

/**
 * Service pour les opérations de gestion des dépositions.
 *
 * Fournit des méthodes pour récupérer toutes les dépositions associées à une plainte spécifique,
 * et pour trouver des dépositions selon des critères spécifiques.
 */
public interface DepositionService {

    /**
     * Récupère toutes les dépositions associées à une plainte spécifique.
     *
     * @param id l'identifiant de la plainte pour laquelle récupérer les dépositions
     * @return une liste d'entités Deposition associées à la plainte spécifiée
     */
    List<Deposition> findAllDeposition(Long id);

    /**
     * Recherche des dépositions selon des critères spécifiques.
     *
     * @param f l'objet DepositionFilterRequest contenant les critères de recherche
     * @return une liste d'entités Deposition correspondant aux critères de recherche spécifiés, sous forme de DepositionShortResponse
     */
    List<DepositionShortResponse> findByCriteria(DepositionFilterRequest f);

}

