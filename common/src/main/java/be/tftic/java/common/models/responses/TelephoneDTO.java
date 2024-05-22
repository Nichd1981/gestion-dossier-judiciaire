package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Telephone;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TelephoneDTO {

    String numero;
    String libelle;

    public static TelephoneDTO fromEntity(Telephone telephone) {
        return TelephoneDTO.builder()
                .numero(telephone.getNumero())
                .libelle(telephone.getLibelle())
                .build();
    }

}
