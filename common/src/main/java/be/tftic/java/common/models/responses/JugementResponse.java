package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Jugement;
import be.tftic.java.domain.enums.JugementDecision;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Classe de transfert de données pour les jugements.
 * Cette classe est utilisée pour transférer les données d'un jugement
 * depuis la couche de persistance vers la couche de présentation.
 */
@Builder
@Data
public class JugementResponse {

    /**
     * La date à laquelle le jugement a été rendu.
     */
    private LocalDateTime dateJugement;

    /**
     * La décision qui a été prise lors du jugement.
     */
    private JugementDecision jugementDecision;

    /**
     * Un commentaire facultatif qui peut être ajouté au jugement.
     */
    private String commentaire;

    /**
     * Construit une instance de JugementResponse à partir d'une entité Jugement.
     *
     * @param jugement l'entité Jugement à partir de laquelle construire la réponse
     * @return une nouvelle instance de JugementResponse
     */
    public static JugementResponse fromEntity(Jugement jugement){
        return JugementResponse.builder()
                .dateJugement(jugement.getDateJugement())
                .jugementDecision(jugement.getJugementDecision())
                .commentaire(jugement.getCommentaire())
                .build();
    }
}
