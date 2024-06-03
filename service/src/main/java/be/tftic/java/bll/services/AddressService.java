package be.tftic.java.bll.services;

import be.tftic.java.domain.entities.Address;
import java.nio.file.AccessDeniedException;

/**
 * Interface pour le service AdresseService.
 * Cette interface définit les méthodes que doit fournir une implémentation du service AdresseService.
 * La séparation entre l'interface et l'implémentation permet de garantir que les méthodes nécessaires sont fournies
 * et facilite le remplacement ou l'extension de l'implémentation.
 */
public interface AddressService {

    /**
     * Met à jour l'adresse avec l'identifiant donné en utilisant les informations fournies.
     *
     * @param id l'identifiant unique de l'adresse à mettre à jour.
     * @param address l'objet Adresse contenant les nouvelles informations de l'adresse.
     * Les propriétés de cet objet sont utilisées pour remplacer les valeurs correspondantes de l'adresse existante.
     * @return l'identifiant de l'adresse mise à jour, qui est le même que l'identifiant fourni en entrée.
     * @throws RuntimeException si l'adresse à mettre à jour n'est pas trouvée dans la base de données.
     */
    Long update(Long id, Address address);
}
