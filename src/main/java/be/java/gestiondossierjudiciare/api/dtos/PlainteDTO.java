package be.java.gestiondossierjudiciare.api.dtos;
import be.java.gestiondossierjudiciare.domain.entities.Jugement;
import be.java.gestiondossierjudiciare.domain.entities.Personne;
import be.java.gestiondossierjudiciare.domain.entities.Plainte;
import be.java.gestiondossierjudiciare.domain.enums.Statut;
import be.java.gestiondossierjudiciare.domain.entities.Audition;

import java.time.LocalDateTime;
import java.util.Set;

public record PlainteDTO(
        Long id,
        String numeroDossier,
        Statut statut,
        LocalDateTime datePlainte,
        Jugement jugement,
        Personne plaignant,
        Personne agentTraitant,
        Set<Personne> personnesConcernes,
        Set<Audition> auditions
) {
    public static PlainteDTO fromEntity(Plainte p) {
        return new PlainteDTO(p.getId(),
                p.getNumeroDossier(),
                p.getStatut(),
                p.getDatePlainte(),
                p.getJugement(),
                p.getPlaignant(),
                p.getAgentTraitant(),
                p.getPersonnesConcernes(),
                p.getAuditions());
    }
}
