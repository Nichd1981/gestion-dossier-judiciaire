package be.java.gestiondossierjudiciare.api.forms;

import be.java.gestiondossierjudiciare.domain.entities.Telephone;
import jakarta.validation.constraints.NotBlank;

public record TelephoneUpdateForm(

    @NotBlank(message = "Numéro ne peut pas être vide")
    String numero,
    @NotBlank(message = "Libellé ne peut pas être blanc")
    String libelle

){

    public Telephone toEntity(){

        Telephone telephone = new Telephone();
        telephone.setNumero(numero);
        telephone.setLibelle(libelle);
        return telephone;

    }

}