package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Telephone;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TelephoneResponse {

    String numero;
    String libelle;

    public static TelephoneResponse fromEntity(Telephone telephone) {
        return TelephoneResponse.builder()
                .numero(telephone.getNumero())
                .libelle(telephone.getLibelle())
                .build();
    }

}
