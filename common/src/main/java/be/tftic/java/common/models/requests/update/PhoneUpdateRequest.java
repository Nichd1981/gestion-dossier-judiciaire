package be.tftic.java.common.models.requests.update;

import be.tftic.java.domain.entities.Phone;
import jakarta.validation.constraints.NotBlank;

public record PhoneUpdateRequest(

    @NotBlank(message = "Numéro ne peut pas être vide")
    String number,
    @NotBlank(message = "Libellé ne peut pas être blanc")
    String label

){

    public Phone toEntity(){

        return Phone.builder()
                .number(number)
                .label(label)
                .build();

    }

}