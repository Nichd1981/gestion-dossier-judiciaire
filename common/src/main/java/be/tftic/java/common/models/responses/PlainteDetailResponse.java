package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class PlainteDetailResponse {

    String numeroDossier;
    String statut;
    LocalDateTime date;
    PersonneShortResponse plaignant;
    PersonneShortResponse agentTraitant;
    List<PersonneShortResponse> personnesConcernees;
//    Jugement jugement;
//    List<Audition> auditions;
//    List<PlainteDepositionHistorique> depositions;


    public static PlainteDetailResponse fromEntity(Plainte plainte) {
        return PlainteDetailResponse.builder()
                .numeroDossier(plainte.getNumeroDossier())
                .statut(plainte.getStatut().toString())
                .date(plainte.getDatePlainte())
                .plaignant(PersonneShortResponse.fromEntity(plainte.getPlaignant()))
                .agentTraitant(PersonneShortResponse.fromEntity(plainte.getAgentTraitant()))
                .personnesConcernees(plainte.getPersonnesConcernees().stream().map(PersonneShortResponse::fromEntity).toList())
//                .jugement(plainte.getJugement())
//                .auditions(plainte.getAuditions())
//                .depositions(plainte.getDepositions())
                .build();
    }

}
