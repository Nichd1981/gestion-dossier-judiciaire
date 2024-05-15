package be.java.gestiondossierjudiciare.api.forms;

import be.java.gestiondossierjudiciare.domain.entities.Citoyen;
import be.java.gestiondossierjudiciare.domain.enums.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CitoyenUpdateForm (

        @NotBlank(message = "Nom ne peut pas être vide")
        @Size(min = 1, max = 50)
        String nom,
        @NotBlank(message = "Prénom ne peut pas être vide")
        @Size(min = 1, max = 50)
        String prenom,
        @NotBlank(message = "Genre ne peut pas être vide")
        Genre genre,
        String photo,
        String empreinte,
        @NotBlank(message = "Date de naissance ne peut pas être vide")
        LocalDateTime dateDeNaissance,
        @NotBlank(message = "Lieu de naissance ne peut pas être vide")
        String lieuDeNaissance

){

    public Citoyen toEntity(){
        Citoyen citoyen = new Citoyen();
        citoyen.setNom(nom);
        citoyen.setPrenom(prenom);
        citoyen.setGenre(genre);
        citoyen.setPhoto(photo);
        citoyen.setEmpreinte(empreinte);
        citoyen.setDateNaissance(dateDeNaissance);
        citoyen.setLieuNaissance(lieuDeNaissance);
        return citoyen;
    }

}
