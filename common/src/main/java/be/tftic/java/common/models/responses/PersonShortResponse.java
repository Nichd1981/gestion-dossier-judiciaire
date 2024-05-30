package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Person;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Classe de réponse courte pour les personnes.
 * Cette classe est annotée avec @Data de Lombok pour générer automatiquement les
 * getters, setters, constructeurs, et méthodes toString(), hashCode(),
 * et equals(). Elle est utilisée pour stocker et transmettre les informations les plus
 * importantes d'une personne dans la réponse HTTP.
 *
 */
@Builder
@Data
public class PersonShortResponse {

    /**
     * Numéro de registre national de la personne.
     */
    String nationalRegister;

    /**
     * Nom de famille de la personne.
     */
    String name;

    /**
     * Prénom de la personne.
     */
    String firstname;

    /**
     * Date de naissance de la personne.
     */
    LocalDateTime birthDate;

    /**
     * Lieu de naissance de la personne.
     */
    String birthPlace;

    /**
     * Genre de la personne.
     */
    String gender;

    /**
     * Méthode de création d'une instance de PersonneShortResponse à partir d'une entité
     * Personne.
     *
     * @param person l'entité Personne à partir de laquelle créer l'instance de
     *                   PersonneShortResponse
     * @return l'instance de PersonneShortResponse créée à partir de l'entité 
     *         Personne
     */
    public static PersonShortResponse fromEntity(Person person){
        return PersonShortResponse.builder()
                .nationalRegister(person.getNationalRegister())
                .name(person.getName())
                .firstname(person.getFirstname())
                .birthDate(person.getBirthdate())
                .birthPlace(person.getBirthplace())
                .gender(person.getGender().toString())
                .build();
    }
}
