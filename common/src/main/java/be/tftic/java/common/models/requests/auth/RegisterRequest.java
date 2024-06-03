package be.tftic.java.common.models.requests.auth;

import be.tftic.java.common.annotations.ValidPassword;
import be.tftic.java.common.models.requests.create.PersonCreateRequest;
import be.tftic.java.domain.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public final class RegisterRequest {

    @NotBlank
    @Email
    private final String mail;

    @NotBlank
    @ValidPassword
    private final String password;

    private final PersonCreateRequest person;

    public RegisterRequest(String mail, String password, PersonCreateRequest person) {
        this.mail = mail;
        this.password = password;
        this.person = person;
    }

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
