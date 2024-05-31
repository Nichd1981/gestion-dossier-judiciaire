package be.tftic.java.bll.services;

import be.tftic.java.common.models.requests.create.ComplaintCreateRequest;
import be.tftic.java.common.models.requests.filter.ComplaintFilterRequest;
import be.tftic.java.common.models.requests.update.ClosedSurveyRequest;
import be.tftic.java.common.models.responses.ComplaintShortResponse;
import be.tftic.java.domain.entities.Complaint;
import java.util.List;

/**
 * Interface pour les opérations de gestion des plaintes.
 * Fournit des méthodes pour récupérer toutes les plaintes, trouver une plainte par son identifiant ou son numéro de dossier,
 * trouver des plaintes associées à un plaignant ou une personne concernée, rechercher des plaintes selon des critères spécifiques,
 * créer une nouvelle plainte, ouvrir une enquête sur une plainte existante, et clôturer une enquête sur une plainte.
 */
public interface ComplaintService {

    /**
     * Récupère toutes les plaintes disponibles.
     *
     * @return une liste d'entités Plainte représentant toutes les plaintes disponibles
     */
    List<ComplaintShortResponse> findAll();

    /**
     * Récupère une plainte par son identifiant.
     *
     * @param id l'identifiant de la plainte à récupérer
     * @return l'entité Plainte correspondant à l'identifiant spécifié
     */
    Complaint findById(Long id);

    /**
     * Récupère une plainte par son numéro de dossier.
     *
     * @param fileNumber le numéro de dossier de la plainte à récupérer
     * @return l'entité Plainte correspondant au numéro de dossier spécifié
     */
    Complaint findByFileNumber(String fileNumber);

    /**
     * Récupère toutes les plaintes associées à un plaignant spécifique.
     * @return une liste d'entités Plainte associées au plaignant spécifié
     */
    List<ComplaintShortResponse> findByComplainantId();

    /**
     * Récupère toutes les plaintes associées à une personne concernée spécifique.
     * @return une liste d'entités Plainte associées à la personne concernée spécifiée
     */
    List<ComplaintShortResponse> findByPersonConcerned();

    /**
     * Recherche des plaintes selon des critères spécifiques.
     * @return une liste d'entités Plainte correspondant aux critères de recherche spécifiés
     */
    List<ComplaintShortResponse> findByCriteria(ComplaintFilterRequest f);

    /**
     * Crée une nouvelle plainte à partir d'un formulaire de création.
     *
     * @param form l'objet PlainteCreateRequest contenant les informations de création de la plainte
     * @return l'entité Plainte créée
     */
    Complaint create(ComplaintCreateRequest form);

    /**
     * Ouvre une enquête sur une plainte existante.
     *
     * @param id l'identifiant de la plainte sur laquelle ouvrir l'enquête
     */
    void openSurvey(Long id);

    /**
     * Clôture une enquête sur une plainte existante.
     *
     * @param form l'objet ClotureEnqueteRequest contenant les informations de clôture de l'enquête
     */
    void closedSurvey(ClosedSurveyRequest form);

    /**
     * Recherche des plaintes associées à un plaignant selon des critères spécifiques.
     * @return une liste d'entités Plainte correspondant aux critères de recherche spécifiés
     */
    List<ComplaintShortResponse> findByComplaintIdWithCriteria(ComplaintFilterRequest f);

    List<ComplaintShortResponse> getComplaintByCustomerAndLawyer(Long customerId);

}
