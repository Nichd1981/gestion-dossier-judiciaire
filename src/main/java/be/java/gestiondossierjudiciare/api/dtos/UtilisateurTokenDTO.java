package be.java.gestiondossierjudiciare.api.dtos;

import be.java.gestiondossierjudiciare.domain.entities.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilisateurTokenDTO {
    private Long id;
    private String email;
    private String token;

    public static UtilisateurTokenDTO fromEntity(Utilisateur utilisateur) {
        return UtilisateurTokenDTO.builder()
                .id(utilisateur.getId())
                .email(utilisateur.getEmail())
                .build();
    }
}
