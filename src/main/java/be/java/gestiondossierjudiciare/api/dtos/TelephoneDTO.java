package be.java.gestiondossierjudiciare.api.dtos;

import be.java.gestiondossierjudiciare.domain.entities.Telephone;
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
