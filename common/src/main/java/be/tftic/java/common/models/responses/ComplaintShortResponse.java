package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Complaint;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Classe de réponse courte pour les plaintes.
 * Cette classe est annotée avec @Data de Lombok pour générer automatiquement les
 * getters, setters, constructeurs, et méthodes toString(), hashCode(),
 * et equals(). Elle est utilisée pour stocker et transmettre les informations les plus
 * importantes d'une plainte dans la réponse HTTP.
 *
 */
@Builder
@Data
public class ComplaintShortResponse {

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
    PersonShortResponse complaint;

    /**
     * Informations courtes sur l'agent traitant la plainte.
     */
    PersonShortResponse agent;

    /**
     * Méthode de création d'une instance de PlainteShortResponse à partir d'une entité
     * Plainte.
     *
     * @param complaint l'entité Plainte à partir de laquelle créer l'instance de
     *                PlainteShortResponse
     * @return l'instance de PlainteShortResponse créée à partir de l'entité
     * Plainte
     */
    public static ComplaintShortResponse fromEntity(Complaint complaint) {
        return ComplaintShortResponse.builder()
                .fileNumber(complaint.getFileNumber())
                .status(complaint.getStatus().toString())
                .date(complaint.getComplaintDate())
                .complaint(PersonShortResponse.fromEntity(complaint.getComplainant()))
                .agent(PersonShortResponse.fromEntity(complaint.getAgent()))
                .build();
    }
}