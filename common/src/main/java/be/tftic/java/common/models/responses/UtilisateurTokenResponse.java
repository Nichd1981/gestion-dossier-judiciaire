package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilisateurTokenResponse {

    Long id;
    String email;
    String token;

    public static UtilisateurTokenResponse fromEntity(Utilisateur utilisateur) {
        return UtilisateurTokenResponse.builder()
                .id(utilisateur.getId())
                .email(utilisateur.getEmail())
                .build();
    }
}
