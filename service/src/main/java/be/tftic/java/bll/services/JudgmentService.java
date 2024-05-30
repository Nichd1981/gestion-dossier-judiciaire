package be.tftic.java.bll.services;

import be.tftic.java.common.models.requests.update.JudgmentUpdateRequest;
import be.tftic.java.common.models.responses.JudgmentResponse;
import java.time.LocalDate;
import java.util.List;

/**
 * Interface pour les opérations de gestion des jugements.
 * Fournit des méthodes pour la création de jugements, la récupération de tous les jugements associés à une plainte spécifique,
 * la recherche de jugements selon des critères spécifiques, et la clôture d'un jugement.
 */
public interface JudgmentService {

    /**
     * Crée un nouveau jugement associé à une plainte spécifique.
     *
     * @param complaintId l'identifiant de la plainte pour laquelle créer le jugement
     */
    void create(Long complaintId);

    /**
     * Récupère tous les jugements associés à une plainte spécifique.
     *
     * @param complaintId l'identifiant de la plainte pour laquelle récupérer les jugements
     * @return une liste d'entités Jugement associées à la plainte spécifiée
     */
    List<JudgmentResponse> findAllForComplaint(Long complaintId);

    /**
     * Recherche des jugements selon des critères spécifiques.
     *
     * @param complaintId l'identifiant de la plainte pour laquelle effectuer la recherche de jugements
     * @param fileNumber le numéro de dossier du jugement
     * @param lowerBound la date de début de la période de recherche
     * @param upperBound la date de fin de la période de recherche
     * @param keyWord le mot-clé à rechercher dans les jugements
     * @param decision la décision du jugement
     * @return une liste d'entités Jugement correspondant aux critères de recherche spécifiés
     */
    List<JudgmentResponse> findWithCriteria(Long complaintId, String fileNumber, LocalDate lowerBound, LocalDate upperBound, String keyWord, String decision);

    /**
     * Clôture un jugement en mettant à jour ses informations.
     *
     * @param judgment l'objet JugementUpdateRequest contenant les informations de mise à jour du jugement à clôturer
     */
    void closedJudgment(JudgmentUpdateRequest judgment);

}
