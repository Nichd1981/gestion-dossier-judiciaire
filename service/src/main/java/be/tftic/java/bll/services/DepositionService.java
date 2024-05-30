package be.tftic.java.bll.services;

import be.tftic.java.common.models.requests.filter.DepositionFilterRequest;
import be.tftic.java.common.models.responses.DepositionShortResponse;
import java.util.List;

/**
 * Interface pour les opérations de gestion des dépositions.
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
    List<DepositionShortResponse> findAllDeposition(Long id);

    /**
     * Recherche des dépositions selon des critères spécifiques.
     *
     * @param f l'objet DepositionFilterRequest contenant les critères de recherche
     * @return une liste d'entités Deposition correspondant aux critères de recherche spécifiés, sous forme de DepositionShortResponse
     */
    List<DepositionShortResponse> findByCriteria(DepositionFilterRequest f);

}
