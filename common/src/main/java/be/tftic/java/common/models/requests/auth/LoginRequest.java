package be.tftic.java.common.models.requests.auth;

import be.tftic.java.domain.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Classe de requête de connexion.
 * Cette classe est utilisée pour stocker les informations nécessaires à la connexion d'un utilisateur,
 * telles que l'adresse e-mail et le mot de passe.
 *
 */
public record LoginRequest(

        /*** L'adresse e-mail de l'utilisateur.*/
        @Email
        @NotBlank
        String mail,

        /**
         * Le mot de passe de l'utilisateur.
         */
        @NotBlank
        String password
) {
    /**
     * Méthode pour convertir la requête de connexion en une entité d'utilisateur.
     *
     * @return l'entité d'utilisateur créée à partir de la requête de connexion
     */
    public User toEntity() {
        return User.builder()
                .mail(mail)
                .password(password)
                .build();
    }
}