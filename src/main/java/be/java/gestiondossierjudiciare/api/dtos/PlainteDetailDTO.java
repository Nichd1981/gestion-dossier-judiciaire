package be.java.gestiondossierjudiciare.api.dtos;

import be.java.gestiondossierjudiciare.domain.entities.*;
import be.java.gestiondossierjudiciare.domain.enums.Statut;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public class PlainteDetailDTO {

    private String numeroDossier;
    private Statut statut;
    private LocalDateTime date;
    private Personne plaignant;
    private Personne agentTraitant;
    private Jugement jugement;
    private Set<Personne> personnesConcernees;
    private Set<Audition> auditions;
    private Set<PlainteDepositionHistorique> depositions;
    private Set<PlainteTypeHistorique> types;


    public static PlainteDetailDTO fromEntity(Plainte plainte) {
        return PlainteDetailDTO.builder()
                .numeroDossier(plainte.getNumeroDossier())
                .statut(plainte.getStatut())
                .date(plainte.getDatePlainte())
                .plaignant(plainte.getPlaignant())
                .agentTraitant(plainte.getAgentTraitant())
                .jugement(plainte.getJugement())
                .personnesConcernees(plainte.getPersonnesConcernees())
                .auditions(plainte.getAuditions())
                .depositions(plainte.getDepositions())
                .types(plainte.getTypes())
                .build();
    }

}
