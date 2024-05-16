package be.java.gestiondossierjudiciare.api.dtos;

import be.java.gestiondossierjudiciare.domain.entities.Connexion;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConnexionTokenDTO {
    private Long id;
    private String email;
    private String token;

    public static ConnexionTokenDTO fromEntity(Connexion connexion) {
        return ConnexionTokenDTO.builder()
                .id(connexion.getId())
                .email(connexion.getEmail())
                .build();
    }
}
