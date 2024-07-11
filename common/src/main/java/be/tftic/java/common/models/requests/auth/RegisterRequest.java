package be.tftic.java.common.models.requests.auth;

import be.tftic.java.common.annotations.StrongPassword;
import be.tftic.java.common.models.requests.create.PersonCreateRequest;
import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.entities.User;
import be.tftic.java.domain.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public final class RegisterRequest {

    @NotBlank
    @Email
    private final String mail;

    @NotBlank
//    @StrongPassword
    private final String password;

    String nationalRegisterNumber;
    String firstName;
    String lastName;
    LocalDate birthDate;
    String birthPlace;
    String gender;
    String picture;

    public RegisterRequest(String password, String mail, String nationalRegisterNumber, String firstName, String lastName, LocalDate birthDate, String birthPlace, String gender, String picture) {
        this.password = password;
        this.mail = mail;
        this.nationalRegisterNumber = nationalRegisterNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.gender = gender;
        this.picture = picture;
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
                .person(Person.builder()
                        .name(firstName)
                        .firstname(lastName)
                        .nationalRegister(nationalRegisterNumber)
                        .birthdate(birthDate)
                        .birthplace(birthPlace)
                        .gender((gender.isBlank()) ? null : Gender.valueOf(gender))
                        .picture(picture)
                        .build())
                .build();
    }

    public PersonCreateRequest getPerson(){
        return new PersonCreateRequest(nationalRegisterNumber, firstName, lastName, birthDate, birthPlace, gender, picture);
    }

}
