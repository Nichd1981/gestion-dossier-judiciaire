package be.tftic.java.common.models.requests;

import be.tftic.java.domain.entities.Telephone;
import jakarta.validation.constraints.NotBlank;

public record TelephoneUpdateForm(

    @NotBlank(message = "Numéro ne peut pas être vide")
    String numero,
    @NotBlank(message = "Libellé ne peut pas être blanc")
    String libelle

){

    public Telephone toEntity(){

        return Telephone.builder()
                .numero(numero)
                .libelle(libelle)
                .build();

    }

}