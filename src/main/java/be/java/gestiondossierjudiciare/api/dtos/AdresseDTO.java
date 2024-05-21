package be.java.gestiondossierjudiciare.api.dtos;

import be.java.gestiondossierjudiciare.domain.entities.Adresse;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdresseDTO {

    String rue;
    String numero;
    String ville;
    String codePostal;
    String pays;
    String libelle;

    public static AdresseDTO fromEntity(Adresse adresse) {
        return AdresseDTO.builder()
                .rue(adresse.getRue())
                .numero(adresse.getNumero())
                .ville(adresse.getVille())
                .codePostal(adresse.getCodePostal())
                .pays(adresse.getPays())
                .libelle(adresse.getLibelle())
                .build();
    }

}
