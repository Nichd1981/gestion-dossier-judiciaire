package be.java.gestiondossierjudiciare.api.dtos;

import be.java.gestiondossierjudiciare.domain.entities.Personne;
import be.java.gestiondossierjudiciare.domain.entities.Plainte;
import be.java.gestiondossierjudiciare.domain.enums.Statut;
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
