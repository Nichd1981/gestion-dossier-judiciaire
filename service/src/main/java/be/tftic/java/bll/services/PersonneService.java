package be.tftic.java.bll.services;


import be.tftic.java.domain.entities.Personne;

/**
 * Service pour les opérations de gestion des personnes.
 *
 * Fournit des méthodes pour récupérer une personne par son identifiant et pour mettre à jour les informations d'une personne.
 */
public interface PersonneService {

    /**
     * Récupère une personne par son identifiant.
     *
     * @param id l'identifiant de la personne à récupérer
     * @return l'entité Personne correspondant à l'identifiant spécifié
     */
    Personne findById(Long id);

    /**
     * Met à jour les informations d'une personne spécifiée par son identifiant.
     *
     * @param id l'identifiant de la personne à mettre à jour
     * @param personne l'objet Personne contenant les nouvelles informations de la personne
     * @return l'identifiant de la personne mise à jour
     */
    Long update(Long id, Personne personne);

}

