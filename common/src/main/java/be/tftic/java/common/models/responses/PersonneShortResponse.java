package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Personne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Classe de réponse courte pour les personnes.
 *
 * Cette classe est annotée avec @Data de Lombok pour générer automatiquement les
 * getters, setters, constructeurs, et méthodes toString(), hashCode(),
 * et equals(). Elle est utilisée pour stocker et transmettre les informations les plus
 * importantes d'une personne dans la réponse HTTP.
 *
 */
@Builder
@Data
public class PersonneShortResponse {

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
     * Méthode de création d'une instance de PersonneShortResponse à partir d'une entité
     * Personne.
     *
     * @param personne l'entité Personne à partir de laquelle créer l'instance de
     *                   PersonneShortResponse
     * @return l'instance de PersonneShortResponse créée à partir de l'entité 
     *         Personne
     */
    public static PersonneShortResponse fromEntity(Personne personne){
        return PersonneShortResponse.builder()
                .registreNational(personne.getRegistreNational())
                .nom(personne.getNom())
                .prenom(personne.getPrenom())
                .dateNaissance(personne.getDateNaissance())
                .lieuNaissance(personne.getLieuNaissance())
                .genre(personne.getGenre().toString())
                .build();
    }
}
