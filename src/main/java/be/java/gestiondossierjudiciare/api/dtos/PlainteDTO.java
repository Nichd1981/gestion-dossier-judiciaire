package be.java.gestiondossierjudiciare.api.dtos;

import be.java.gestiondossierjudiciare.domain.entities.Personne;
import be.java.gestiondossierjudiciare.domain.entities.Plainte;
import be.java.gestiondossierjudiciare.domain.enums.Statut;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class PlainteDTO {

    private String numeroDossier;
    private Statut statut;
    private LocalDateTime date;
    private Personne plaignant;
    private Personne agentTraitant;


    public static PlainteDTO fromEntity(Plainte plainte) {
        return PlainteDTO.builder()
                .numeroDossier(plainte.getNumeroDossier())
                .statut(plainte.getStatut())
                .date(plainte.getDatePlainte())
                .plaignant(plainte.getPlaignant())
                .agentTraitant(plainte.getAgentTraitant())
                .build();
    }

}
