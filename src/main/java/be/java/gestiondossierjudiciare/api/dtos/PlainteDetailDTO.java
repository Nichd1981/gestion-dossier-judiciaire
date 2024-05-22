package be.java.gestiondossierjudiciare.api.dtos;

import be.java.gestiondossierjudiciare.domain.entities.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class PlainteDetailDTO {

    String numeroDossier;
    String statut;
    LocalDateTime date;
    PersonneShortDTO plaignant;
    PersonneShortDTO agentTraitant;
    List<PersonneShortDTO> personnesConcernees;
//    Jugement jugement;
//    List<Audition> auditions;
//    List<PlainteDepositionHistorique> depositions;


    public static PlainteDetailDTO fromEntity(Plainte plainte) {
        return PlainteDetailDTO.builder()
                .numeroDossier(plainte.getNumeroDossier())
                .statut(plainte.getStatut().toString())
                .date(plainte.getDatePlainte())
                .plaignant(PersonneShortDTO.fromEntity(plainte.getPlaignant()))
                .agentTraitant(PersonneShortDTO.fromEntity(plainte.getAgentTraitant()))
                .personnesConcernees(plainte.getPersonnesConcernees().stream().map(PersonneShortDTO::fromEntity).toList())
//                .jugement(plainte.getJugement())
//                .auditions(plainte.getAuditions())
//                .depositions(plainte.getDepositions())
                .build();
    }

}
