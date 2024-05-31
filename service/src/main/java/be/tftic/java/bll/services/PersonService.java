package be.tftic.java.bll.services;

import be.tftic.java.common.models.requests.create.PersonCreateRequest;
import be.tftic.java.domain.entities.Person;

/**
 * Interface pour les opérations de gestion des personnes.
 * Fournit des méthodes pour récupérer une personne par son identifiant et pour mettre à jour les informations d'une personne.
 */
public interface PersonService {

    /**
     * Creates a new Person entity and saves it in DB
     * @param request DTO containing required information to create a person
     */
    Person create(PersonCreateRequest request);

    /**
     * Récupère une personne par son identifiant.
     *
     * @param id l'identifiant de la personne à récupérer
     * @return l'entité Personne correspondant à l'identifiant spécifié
     */
    Person findById(Long id);

    /**
     * Finds a person by nation register number
     * @param nationalRegister National register number of the person to seek
     * @return Person entity corresponding
     */
    Person findByNationalRegister(String nationalRegister);

    /**
     * Checks if a person exists by National register number
     * @param nationalRegister National register number of the person to seek
     * @return True if that person exists, false otherwise
     */
    boolean existsByNationalRegister(String nationalRegister);

    /**
     * Met à jour les informations d'une personne spécifiée par son identifiant.
     *
     * @param id l'identifiant de la personne à mettre à jour
     * @param person l'objet Personne contenant les nouvelles informations de la personne
     * @return l'identifiant de la personne mise à jour
     */
    Long update(Long id, Person person);

}
