package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Deposition;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * Classe représentant une version abrégée d'une déposition.
 *
 * Cette classe est utilisée pour représenter une version abrégée d'une déposition,
 * avec seulement la date et le contenu de la déposition. Elle est utilisée pour
 * transférer les données de la déposition de la couche de persistance vers la
 * couche de présentation.
 */
@Builder
@Data
public class DepositionShortResponse {

    /**
     * La date de la déposition.
     */
    LocalDate date;

    /**
     * Le contenu de la déposition.
     */
    String deposition;

    /**
     * Construit une nouvelle instance de {@code DepositionShortResponse} à partir
     * d'une entité {@code Deposition}.
     *
     * @param deposition l'entité {@code Deposition} à partir de laquelle créer
     *                   la nouvelle instance de {@code DepositionShortResponse}
     * @return une nouvelle instance de {@code DepositionShortResponse}
     */
    public static DepositionShortResponse fromEntity(Deposition deposition) {
        return DepositionShortResponse.builder()
                .date(deposition.getDateDeposition())
                .deposition(deposition.getDeposition())
                .build();
    }
}
