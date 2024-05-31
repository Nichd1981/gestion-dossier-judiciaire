package be.tftic.java.common.models.requests.auth;

import be.tftic.java.common.models.requests.create.PersonCreateRequest;
import be.tftic.java.domain.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(

        @Email
        @NotBlank
        String mail,

        @NotBlank
        String password,

        PersonCreateRequest person

) {

    /**
     * Méthode pour convertir la requête de register en une entité d'utilisateur.
     *
     * @return l'entité d'utilisateur créée à partir de la register de connexion
     */
    public User toEntity() {
        return User.builder()
                .mail(mail)
                .password(password)
                .build();
    }

}
