package be.tftic.java.common.models.requests.create;

import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.enums.Statut;

import java.time.LocalDateTime;
import java.util.List;

public record PlainteCreateRequest(

        String numeroDossier,
        Long idPlaignant,
        Long idAgentTraitant,
        List<Long> idConcernes

) {

    public Plainte toEntity(){
        return Plainte.builder()
                .numeroDossier(numeroDossier)
                .datePlainte(LocalDateTime.now())
                .statut(Statut.ENREGISTREE)
                .build();
    }


}
