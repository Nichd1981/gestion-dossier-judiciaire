package be.tftic.java.bll.services;


import be.tftic.java.common.models.requests.ClotureEnqueteRequest;
import be.tftic.java.common.models.requests.PlainteCreateRequest;
import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Plainte;

import java.time.LocalDate;
import java.util.List;

/**
 * Service pour les opérations de gestion des plaintes.
 *
 * Fournit des méthodes pour récupérer toutes les plaintes, trouver une plainte par son identifiant ou son numéro de dossier,
 * trouver des plaintes associées à un plaignant ou une personne concernée, rechercher des plaintes selon des critères spécifiques,
 * créer une nouvelle plainte, ouvrir une enquête sur une plainte existante, et clôturer une enquête sur une plainte.
 */
public interface PlainteService {

    /**
     * Récupère toutes les plaintes disponibles.
     *
     * @return une liste d'entités Plainte représentant toutes les plaintes disponibles
     */
    List<Plainte> findAll();

    /**
     * Récupère une plainte par son identifiant.
     *
     * @param id l'identifiant de la plainte à récupérer
     * @return l'entité Plainte correspondant à l'identifiant spécifié
     */
    Plainte findById(Long id);

    /**
     * Récupère une plainte par son numéro de dossier.
     *
     * @param numeroDossier le numéro de dossier de la plainte à récupérer
     * @return l'entité Plainte correspondant au numéro de dossier spécifié
     */
    Plainte findByNumeroDossier(String numeroDossier);

    /**
     * Récupère toutes les plaintes associées à un plaignant spécifique.
     *
     * @param id l'identifiant du plaignant pour lequel récupérer les plaintes
     * @return une liste d'entités Plainte associées au plaignant spécifié
     */
    List<Plainte> findByPlaignantId(Long id);

    /**
     * Récupère toutes les plaintes associées à une personne concernée spécifique.
     *
     * @param personne l'entité Personne concernée pour laquelle récupérer les plaintes
     * @return une liste d'entités Plainte associées à la personne concernée spécifiée
     */
    List<Plainte> findByPersonneConcernee(Personne personne);

    /**
     * Recherche des plaintes selon des critères spécifiques.
     *
     * @param numeroDossier le numéro de dossier de la plainte
     * @param lowerBound la date de début de la période de recherche
     * @param upperBound la date de fin de la période de recherche
     * @param statut le statut de la plainte
     * @return une liste d'entités Plainte correspondant aux critères de recherche spécifiés
     */
    List<Plainte> findByCriteria(String numeroDossier, LocalDate lowerBound, LocalDate upperBound, String statut);

    /**
     * Crée une nouvelle plainte à partir d'un formulaire de création.
     *
     * @param form l'objet PlainteCreateRequest contenant les informations de création de la plainte
     * @return l'entité Plainte créée
     */
    Plainte create(PlainteCreateRequest form);

    /**
     * Ouvre une enquête sur une plainte existante.
     *
     * @param id l'identifiant de la plainte sur laquelle ouvrir l'enquête
     */
    void ouvrirEnquete(Long id);

    /**
     * Clôture une enquête sur une plainte existante.
     *
     * @param form l'objet ClotureEnqueteRequest contenant les informations de clôture de l'enquête
     */
    void cloturerEnquete(ClotureEnqueteRequest form);

    /**
     * Recherche des plaintes associées à un plaignant selon des critères spécifiques.
     *
     * @param plaignant l'entité Personne plaignant pour lequel récupérer les plaintes
     * @param type le type de plainte
     * @param upperBound la date de début de la période de recherche
     * @param lowerBound la date de fin de la période de recherche
     * @param numeroDossier le numéro de dossier de la plainte
     * @param statut le statut de la plainte
     * @return une liste d'entités Plainte correspondant aux critères de recherche spécifiés
     */
    List<Plainte> findByPlaignantIdWithCriteria(Personne plaignant, String type, LocalDate upperBound, LocalDate lowerBound, String numeroDossier, String statut);

}

