package be.java.gestiondossierjudiciare.api.forms;

import be.java.gestiondossierjudiciare.domain.entities.Plainte;
import be.java.gestiondossierjudiciare.domain.enums.Statut;

import java.time.LocalDateTime;
import java.util.List;

public record PlainteCreateForm(

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
