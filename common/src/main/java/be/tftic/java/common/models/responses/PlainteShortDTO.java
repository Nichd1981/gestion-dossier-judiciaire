package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Plainte;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class PlainteShortDTO {

    String numeroDossier;
    String statut;
    LocalDateTime date;
    PersonneShortDTO plaignant;
    PersonneShortDTO agentTraitant;


    public static PlainteShortDTO fromEntity(Plainte plainte) {
        return PlainteShortDTO.builder()
                .numeroDossier(plainte.getNumeroDossier())
                .statut(plainte.getStatut().toString())
                .date(plainte.getDatePlainte())
                .plaignant(PersonneShortDTO.fromEntity(plainte.getPlaignant()))
                .agentTraitant(PersonneShortDTO.fromEntity(plainte.getAgentTraitant()))
                .build();
    }

}
