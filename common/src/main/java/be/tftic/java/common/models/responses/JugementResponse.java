package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Jugement;
import be.tftic.java.domain.enums.JugementDecision;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class JugementResponse {

    private LocalDateTime dateJugement;
    private JugementDecision jugementDecision;
    private String commentaire;

    public static JugementResponse fromEntity(Jugement jugement){
        return JugementResponse.builder()
                .dateJugement(jugement.getDateJugement())
                .jugementDecision(jugement.getJugementDecision())
                .commentaire(jugement.getCommentaire())
                .build();
    }

}
