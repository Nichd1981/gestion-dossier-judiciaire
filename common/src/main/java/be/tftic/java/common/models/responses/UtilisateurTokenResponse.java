package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Utilisateur;
import lombok.Builder;
import lombok.Data;

/**
 * Classe de réponse pour l'authentification JWT.
 *
 * Cette classe est annotée avec @Data de Lombok pour générer automatiquement les
 * getters, setters, constructeurs, et méthodes toString(), hashCode(),
 * et equals(). Elle est utilisée pour stocker et transmettre les informations de
 * l'utilisateur et du jeton JWT dans la réponse HTTP de l'authentification JWT.
 *
 */
@Data
@Builder
public class UtilisateurTokenResponse {

    /**
     * Identifiant unique de l'utilisateur.
     */
    Long id;

    /**
     * Adresse e-mail de l'utilisateur.
     */
    String email;

    /**
     * Jeton JWT pour l'authentification de l'utilisateur.
     */
    String token;

    /**
     * Méthode de création d'une instance de UtilisateurTokenResponse à partir d'une
     * entité Utilisateur.
     *
     * @param utilisateur l'entité Utilisateur à partir de laquelle créer l'instance de UtilisateurTokenResponse.
     *
     * @return l'instance de UtilisateurTokenResponse créée à partir de l'entité Utilisateur.
     */
    public static UtilisateurTokenResponse fromEntity(Utilisateur utilisateur) {
        return UtilisateurTokenResponse.builder()
                .id(utilisateur.getId())
                .email(utilisateur.getEmail())
                .build();
    }
}
