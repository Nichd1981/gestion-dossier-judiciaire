package be.tftic.java.common.models.requests.create;

import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.enums.Genre;

import java.time.LocalDateTime;

public record PersonneCreateRequest(

        String nationalRegisterNumber,
        String firstName,
        String lastName,
        LocalDateTime birthDate,
        String birthPlace,
        String gender,
        String picture

) {

    public Personne toEntity(){
        return Personne.builder()
                .nom(firstName)
                .prenom(lastName)
                .registreNational(nationalRegisterNumber)
                .dateNaissance(birthDate)
                .lieuNaissance(birthPlace)
                .genre((gender.isBlank()) ? null : Genre.valueOf(gender))
                .photo(picture)
                .build();
    }



}
