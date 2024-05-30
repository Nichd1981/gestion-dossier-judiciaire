package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Plainte;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Classe de réponse courte pour les plaintes.
 *
 * Cette classe est annotée avec @Data de Lombok pour générer automatiquement les
 * getters, setters, constructeurs, et méthodes toString(), hashCode(),
 * et equals(). Elle est utilisée pour stocker et transmettre les informations les plus
 * importantes d'une plainte dans la réponse HTTP.
 *
 */
@Builder
@Data
public class PlainteShortResponse {

    /**
     * Numéro de dossier de la plainte.
     */
    String numeroDossier;

    /**
     * Statut de la plainte.
     */
    String statut;

    /**
     * Date de dépôt de la plainte.
     */
    LocalDateTime date;

    /**
     * Informations courtes sur le plaignant.
     */
    PersonneShortResponse plaignant;

    /**
     * Informations courtes sur l'agent traitant la plainte.
     */
    PersonneShortResponse agentTraitant;

    /**
     * Méthode de création d'une instance de PlainteShortResponse à partir d'une entité
     * Plainte.
     *
     * @param plainte l'entité Plainte à partir de laquelle créer l'instance de
     *                PlainteShortResponse
     * @return l'instance de PlainteShortResponse créée à partir de l'entité
     * Plainte
     */
    public static PlainteShortResponse fromEntity(Plainte plainte) {
        return PlainteShortResponse.builder()
                .numeroDossier(plainte.getNumeroDossier())
                .statut(plainte.getStatut().toString())
                .date(plainte.getDatePlainte())
                .plaignant(PersonneShortResponse.fromEntity(plainte.getPlaignant()))
                .agentTraitant(PersonneShortResponse.fromEntity(plainte.getAgentTraitant()))
                .build();
    }
}