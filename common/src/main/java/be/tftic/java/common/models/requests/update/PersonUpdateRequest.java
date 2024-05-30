package be.tftic.java.common.models.requests.update;

import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record PersonUpdateRequest(

        @NotBlank(message = "Nom ne peut pas être vide")
        @Size(min = 1, max = 50)
        String name,
        @NotBlank(message = "Prénom ne peut pas être vide")
        @Size(min = 1, max = 50)
        String firstname,
        @NotNull
        Gender gender,
        String picture,
        String imprint,
        @NotNull
        LocalDateTime birthDate,
        @NotBlank(message = "Lieu de naissance ne peut pas être vide")
        String birthPlace

){

    public Person toEntity(){
        return Person.builder()
                .name(name)
                .firstname(firstname)
                .gender(gender)
                .picture(picture)
                .imprint(imprint)
                .birthdate(birthDate)
                .birthplace(birthPlace)
                .build();
    }

}
