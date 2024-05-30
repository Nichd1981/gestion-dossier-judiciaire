package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe de réponse détaillée pour les plaintes.
 *
 * Cette classe est annotée avec @Data de Lombok pour générer automatiquement les
 * getters, setters, constructeurs, et méthodes toString(), hashCode(),
 * et equals(). Elle est utilisée pour stocker et transmettre les informations détaillées
 * d'une plainte dans la réponse HTTP.
 *
 */
@Builder
@Data
public class ComplaintDetailResponse {

    /**
     * Numéro de dossier de la plainte.
     */
    String fileNumber;

    /**
     * Statut de la plainte.
     */
    String status;

    /**
     * Date de dépôt de la plainte.
     */
    LocalDateTime date;

    /**
     * Informations courtes sur le plaignant.
     */
    PersonShortResponse complainant;

    /**
     * Informations courtes sur l'agent traitant la plainte.
     */
    PersonShortResponse agent;

    /**
     * Liste des personnes concernées par la plainte.
     */
    List<PersonShortResponse> personConcerned;

    /**
     * Méthode de création d'une instance de PlainteDetailResponse à partir d'une entité
     * Plainte.
     *
     * @param plainte l'entité Plainte à partir de laquelle créer l'instance de
     *                PlainteDetailResponse
     * @return l'instance de PlainteDetailResponse créée à partir de l'entité 
     * Plainte
     */
    public static ComplaintDetailResponse fromEntity(Complaint complaint) {
        return ComplaintDetailResponse.builder()
                .fileNumber(complaint.getFileNumber())
                .status(complaint.getStatus().toString())
                .date(complaint.getComplaintDate())
                .complainant(PersonShortResponse.fromEntity(complaint.getComplainant()))
                .agent(PersonShortResponse.fromEntity(complaint.getAgent()))
                .personConcerned(complaint.getPersonConcerned().stream().map(PersonShortResponse::fromEntity).toList())
                //                .jugement(plainte.getJugement())
                //                .auditions(plainte.getAuditions())
                //                .depositions(plainte.getDepositions())
                .build();
    }
}