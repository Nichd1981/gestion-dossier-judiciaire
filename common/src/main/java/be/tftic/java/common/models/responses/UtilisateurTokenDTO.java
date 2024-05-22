package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilisateurTokenDTO {

    Long id;
    String email;
    String token;

    public static UtilisateurTokenDTO fromEntity(Utilisateur utilisateur) {
        return UtilisateurTokenDTO.builder()
                .id(utilisateur.getId())
                .email(utilisateur.getEmail())
                .build();
    }
}
