package be.tftic.java.bll.services;

import be.tftic.java.common.models.requests.create.AuditionCreateRequest;
import be.tftic.java.common.models.requests.filter.AuditionFilterRequest;
import be.tftic.java.common.models.responses.AuditionShortResponse;
import java.util.List;

/**
 * Interface pour les opérations de gestion des auditions.
 * Fournit des méthodes pour récupérer toutes les auditions, trouver des auditions par différents critères,
 * et trouver toutes les auditions associées à une plainte spécifique.
 */
public interface AuditionService {

	void create(AuditionCreateRequest request);

	/**
	 * Récupère toutes les auditions associées à une plainte spécifique.
	 *
	 * @param id l'identifiant de la plainte pour laquelle récupérer les auditions
	 * @return une liste d'entités Audition associées à la plainte spécifiée
	 */
	List<AuditionShortResponse> findAllAudition(Long id);

	/**
	 * Récupère toutes les auditions disponibles.
	 *
	 * @return une liste d'entités Audition représentant toutes les auditions disponibles
	 */
	List<AuditionShortResponse> findAll();

	/**
	 * Recherche des auditions selon des critères spécifiques.
	 * @return une liste d'entités Audition correspondant aux critères de recherche spécifiés
	 */
	List<AuditionShortResponse> findAuditionByCriteria(AuditionFilterRequest f);
}
