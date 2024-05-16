package be.java.gestiondossierjudiciare.api.forms;

import be.java.gestiondossierjudiciare.domain.entities.Adresse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

public record AdresseUpdateForm(

        @NotBlank
        String rue,
        @NotBlank @NumberFormat
        String numero,
        @NotBlank
        String ville,
        @NotBlank @NumberFormat
        String codePostal,
        @NotBlank
        String pays,
        @NotBlank
        String libelle

){

    public Adresse toEntity(){

        return Adresse.builder()
                .rue(rue)
                .numero(numero)
                .codePostal(codePostal)
                .ville(ville)
                .pays(pays)
                .libelle(libelle)
                .build();

    }

}
