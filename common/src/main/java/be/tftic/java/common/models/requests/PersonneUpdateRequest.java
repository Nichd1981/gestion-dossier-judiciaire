package be.tftic.java.common.models.requests;

import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.enums.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record PersonneUpdateRequest(

        @NotBlank(message = "Nom ne peut pas être vide")
        @Size(min = 1, max = 50)
        String nom,
        @NotBlank(message = "Prénom ne peut pas être vide")
        @Size(min = 1, max = 50)
        String prenom,
        @NotNull
        Genre genre,
        String photo,
        String empreinte,
        @NotNull
        LocalDateTime dateDeNaissance,
        @NotBlank(message = "Lieu de naissance ne peut pas être vide")
        String lieuDeNaissance

){

    public Personne toEntity(){
        return Personne.builder()
                .nom(nom)
                .prenom(prenom)
                .genre(genre)
                .photo(photo)
                .empreinte(empreinte)
                .dateNaissance(dateDeNaissance)
                .lieuNaissance(lieuDeNaissance)
                .build();
    }

}
