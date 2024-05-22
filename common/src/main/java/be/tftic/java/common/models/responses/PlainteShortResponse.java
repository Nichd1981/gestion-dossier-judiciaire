package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Plainte;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class PlainteShortResponse {

    String numeroDossier;
    String statut;
    LocalDateTime date;
    PersonneShortResponse plaignant;
    PersonneShortResponse agentTraitant;


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
