package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Personne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe de réponse détaillée pour les personnes.
 *
 * Cette classe est annotée avec @Data de Lombok pour générer automatiquement les
 * getters, setters, constructeurs, et méthodes toString(), hashCode(),
 * et equals(). Elle est utilisée pour stocker et transmettre les informations détaillées
 * d'une personne dans la réponse HTTP.
 *
 */
@Builder
@Data
public class PersonneDetailResponse {

    /**
     * Numéro de registre national de la personne.
     */
    String registreNational;

    /**
     * Nom de famille de la personne.
     */
    String nom;

    /**
     * Prénom de la personne.
     */
    String prenom;

    /**
     * Date de naissance de la personne.
     */
    LocalDateTime dateNaissance;

    /**
     * Lieu de naissance de la personne.
     */
    String lieuNaissance;

    /**
     * Genre de la personne.
     */
    String genre;

    /**
     * Date de décès de la personne, si applicable.
     */
    LocalDateTime dateDeces;

    /**
     * Photo de la personne, si disponible.
     */
    String photo;

    /**
     * Empreinte digitale de la personne, si disponible.
     */
    String empreinte;

    /**
     * Liste des adresses associées à la personne.
     */
    List<AdresseResponse> adresses;

    /**
     * Liste des numéros de téléphone associés à la personne.
     */
    List<TelephoneResponse> telephones;

    /**
     * Méthode de création d'une instance de PersonneDetailResponse à partir d'une entité
     * Personne.
     *
     * @param personne l'entité Personne à partir de laquelle créer l'instance de PersonneDetailResponse.
     *
     * @return l'instance de PersonneDetailResponse créée à partir de l'entité Personne.
     */
    public static PersonneDetailResponse fromEntity(Personne personne){
        return PersonneDetailResponse.builder()
                .registreNational(personne.getRegistreNational())
                .nom(personne.getNom())
                .prenom(personne.getPrenom())
                .dateNaissance(personne.getDateNaissance())
                .lieuNaissance(personne.getLieuNaissance())
                .genre(personne.getGenre().toString())
                .dateDeces(personne.getDateDeces())
                .photo(personne.getPhoto())
                .empreinte(personne.getEmpreinte())
                .adresses(personne.getAdresses().stream().map(AdresseResponse::fromEntity).toList())
                .telephones(personne.getTelephones().stream().map(TelephoneResponse::fromEntity).toList())
                .build();
    }
}
