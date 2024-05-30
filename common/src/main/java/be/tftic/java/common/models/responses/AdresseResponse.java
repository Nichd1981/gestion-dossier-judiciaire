package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Adresse;
import lombok.Builder;
import lombok.Data;

/**
 * Classe représentant une adresse.
 *
 * Cette classe Java est un POJO (Plain Old Java Object) qui représente une adresse.
 * Elle est annotée avec @Builder et @Data de la bibliothèque Lombok pour générer automatiquement le code pour le constructeur,
 * les getters et les setters, ainsi que d'autres méthodes utilitaires.
 *
 */
@Builder
@Data
public class AdresseResponse {

    /**
     * Le nom de la rue de l'adresse.
     */
    String rue;

    /**
     * Le numéro de l'adresse.
     */
    String numero;

    /**
     * Le nom de la ville de l'adresse.
     */
    String ville;

    /**
     * Le code postal de l'adresse.
     */
    String codePostal;

    /**
     * Le nom du pays de l'adresse.
     */
    String pays;

    /**
     * Le libellé de l'adresse.
     */
    String libelle;

    /**
     * Méthode statique pour construire une instance de {@link AdresseResponse} à partir d'un objet {@link Adresse}.
     *
     * @param adresse l'objet Adresse à mapper vers un objet AdresseResponse
     * @return l'objet AdresseResponse construit à partir de l'objet Adresse
     */
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
