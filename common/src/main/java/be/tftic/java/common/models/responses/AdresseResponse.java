package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Adresse;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdresseResponse {

    String rue;
    String numero;
    String ville;
    String codePostal;
    String pays;
    String libelle;

    public static AdresseResponse fromEntity(Adresse adresse) {
        return AdresseResponse.builder()
                .rue(adresse.getRue())
                .numero(adresse.getNumero())
                .ville(adresse.getVille())
                .codePostal(adresse.getCodePostal())
                .pays(adresse.getPays())
                .libelle(adresse.getLibelle())
                .build();
    }

}
