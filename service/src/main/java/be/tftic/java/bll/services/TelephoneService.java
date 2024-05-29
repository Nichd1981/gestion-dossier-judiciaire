package be.tftic.java.bll.services;


import be.tftic.java.domain.entities.Telephone;

/**
 * Service pour les opérations de gestion des téléphones.
 *
 * Fournit une méthode pour mettre à jour les informations d'un téléphone.
 */
public interface TelephoneService {

    /**
     * Met à jour les informations d'un téléphone spécifié par son identifiant.
     *
     * @param id l'identifiant du téléphone à mettre à jour
     * @param telephone l'objet Telephone contenant les nouvelles informations du téléphone
     * @return l'identifiant du téléphone mis à jour
     */
    Long update(Long id, Telephone telephone);

}
