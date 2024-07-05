package be.tftic.java.common.models.requests.update;

import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PersonUpdateRequest(

        @NotBlank(message = "Nom ne peut pas être vide")
        @Size(min = 1, max = 50)
        String lastName,
        @NotBlank(message = "Prénom ne peut pas être vide")
        @Size(min = 1, max = 50)
        String firstName,
        @NotNull
        Gender gender,
        String picture,
        String imprint,
        @NotNull
        LocalDate birthDate,
        @NotBlank(message = "Lieu de naissance ne peut pas être vide")
        String birthPlace

){

    public Person toEntity(){
        return Person.builder()
                .name(lastName)
                .firstname(firstName)
                .gender(gender)
                .picture(picture)
                .imprint(imprint)
                .birthdate(birthDate)
                .birthplace(birthPlace)
                .build();
    }

}
