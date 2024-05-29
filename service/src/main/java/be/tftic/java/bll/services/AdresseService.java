package be.tftic.java.bll.services;

import be.tftic.java.domain.entities.Adresse;

/**
 * Interface pour le service AdresseService.
 * Cette interface définit les méthodes que doit fournir une implémentation du service AdresseService.
 * La séparation entre l'interface et l'implémentation permet de garantir que les méthodes nécessaires sont fournies
 * et facilite le remplacement ou l'extension de l'implémentation.
 */
public interface AdresseService {

    /**
     * Met à jour l'adresse avec l'identifiant donné en utilisant les informations fournies.
     *
     * @param id l'identifiant unique de l'adresse à mettre à jour.
     * @param adresse l'objet Adresse contenant les nouvelles informations de l'adresse.
     * Les propriétés de cet objet sont utilisées pour remplacer les valeurs correspondantes de l'adresse existante.
     * @return l'identifiant de l'adresse mise à jour, qui est le même que l'identifiant fourni en entrée.
     * @throws RuntimeException si l'adresse à mettre à jour n'est pas trouvée dans la base de données.
     */
    Long update(Long id, Adresse adresse);

}