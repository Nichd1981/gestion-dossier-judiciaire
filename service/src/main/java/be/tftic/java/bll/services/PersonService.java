package be.tftic.java.bll.services;

import be.tftic.java.common.models.responses.PersonShortResponse;
import be.tftic.java.domain.entities.Person;

import java.util.List;

/**
 * Interface pour les opérations de gestion des personnes.
 * Fournit des méthodes pour récupérer une personne par son identifiant et pour mettre à jour les informations d'une personne.
 */
public interface PersonService {

    /**
     * Récupère une personne par son identifiant.
     *
     * @param id l'identifiant de la personne à récupérer
     * @return l'entité Personne correspondant à l'identifiant spécifié
     */
    Person findById(Long id);

    Person findByNationalRegister(String nationalRegister);

    /**
     * Met à jour les informations d'une personne spécifiée par son identifiant.
     *
     * @param id l'identifiant de la personne à mettre à jour
     * @param person l'objet Personne contenant les nouvelles informations de la personne
     * @return l'identifiant de la personne mise à jour
     */
    Long update(Long id, Person person);

    List<PersonShortResponse> getCustomersForLawyer(Long lawyerId);

}
