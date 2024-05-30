package be.tftic.java.bll.services;

import be.tftic.java.domain.entities.Phone;
import java.nio.file.AccessDeniedException;

/**
 * Interface pour les opérations de gestion des téléphones.
 * Fournit une méthode pour mettre à jour les informations d'un téléphone.
 */
public interface PhoneService {

    /**
     * Met à jour les informations d'un téléphone spécifié par son identifiant.
     *
     * @param id l'identifiant du téléphone à mettre à jour
     * @param phone l'objet Telephone contenant les nouvelles informations du téléphone
     * @return l'identifiant du téléphone mis à jour
     */
    Long update(Long id, Phone phone) throws AccessDeniedException;

}