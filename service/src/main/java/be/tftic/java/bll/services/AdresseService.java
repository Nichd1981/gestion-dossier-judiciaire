package be.tftic.java.bll.services;


import be.tftic.java.domain.entities.Adresse;

/**
 * Service pour les opérations de gestion des adresses.
 *
 * Fournit des méthodes pour la mise à jour des informations d'une adresse.
 */
public interface AdresseService {

    /**
     * Met à jour les informations d'une adresse spécifiée par son identifiant.
     *
     * @param id l'identifiant de l'adresse à mettre à jour
     * @param adresse l'objet Adresse contenant les nouvelles informations de l'adresse
     * @return l'identifiant de l'adresse mise à jour
     */
    Long update(Long id, Adresse adresse);
}

