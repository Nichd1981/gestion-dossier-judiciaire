package be.tftic.java.bll.services;

import be.tftic.java.domain.entities.Audition;
import be.tftic.java.domain.entities.Personne;

import java.time.LocalDate;
import java.util.List;


/**
 * Service pour les opérations de gestion des auditions.
 *
 * Fournit des méthodes pour récupérer toutes les auditions, trouver des auditions par différents critères,
 * et trouver toutes les auditions associées à une plainte spécifique.
 */
public interface AuditionService {

	/**
	 * Récupère toutes les auditions associées à une plainte spécifique.
	 *
	 * @param id l'identifiant de la plainte pour laquelle récupérer les auditions
	 * @return une liste d'entités Audition associées à la plainte spécifiée
	 */
	List<Audition> findAllAudition(Long id);

	/**
	 * Récupère toutes les auditions disponibles.
	 *
	 * @return une liste d'entités Audition représentant toutes les auditions disponibles
	 */
	List<Audition> findAll();

	/**
	 * Recherche des auditions selon des critères spécifiques.
	 *
	 * @param personne l'entité Personne associée à l'audition
	 * @param lowerBound la date de début de la période de recherche
	 * @param upperBound la date de fin de la période de recherche
	 * @param motCle le mot-clé à rechercher dans les auditions
	 * @return une liste d'entités Audition correspondant aux critères de recherche spécifiés
	 */
	List<Audition> findAuditionByCriteria(Personne personne, LocalDate lowerBound, LocalDate upperBound, String motCle);
}

