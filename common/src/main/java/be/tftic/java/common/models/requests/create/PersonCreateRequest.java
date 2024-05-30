package be.tftic.java.common.models.requests.create;

import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.enums.Gender;
import java.time.LocalDateTime;

public record PersonCreateRequest(

        String nationalRegisterNumber,
        String firstName,
        String lastName,
        LocalDateTime birthDate,
        String birthPlace,
        String gender,
        String picture

) {

    public Person toEntity(){
        return Person.builder()
                .name(firstName)
                .firstname(lastName)
                .nationalRegister(nationalRegisterNumber)
                .birthdate(birthDate)
                .birthplace(birthPlace)
                .gender((gender.isBlank()) ? null : Gender.valueOf(gender))
                .picture(picture)
                .build();
    }
}
