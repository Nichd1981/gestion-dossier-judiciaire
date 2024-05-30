package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Person;
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
public class PersonDetailResponse {

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
     * Date de décès de la personne, si applicable.
     */
    LocalDateTime deathDate;

    /**
     * Photo de la personne, si disponible.
     */
    String picture;

    /**
     * Empreinte digitale de la personne, si disponible.
     */
    String imprint;

    /**
     * Liste des adresses associées à la personne.
     */
    List<AdresseResponse> address;

    /**
     * Liste des numéros de téléphone associés à la personne.
     */
    List<TelephoneResponse> phones;

    /**
     * Méthode de création d'une instance de PersonneDetailResponse à partir d'une entité
     * Personne.
     *
     * @param personne l'entité Personne à partir de laquelle créer l'instance de PersonneDetailResponse.
     *
     * @return l'instance de PersonneDetailResponse créée à partir de l'entité Personne.
     */
    public static PersonDetailResponse fromEntity(Person person){
        return PersonDetailResponse.builder()
                .nationalRegister(person.getNationalRegister())
                .name(person.getName())
                .firstname(person.getFirstname())
                .birthDate(person.getBirthdate())
                .birthPlace(person.getBirthplace())
                .gender(person.getGender().toString())
                .deathDate(person.getDeathDate())
                .picture(person.getPicture())
                .imprint(person.getImprint())
                .address(person.getAddress().stream().map(AddressResponse::fromEntity).toList())
                .phones(person.getPhones().stream().map(PhoneResponse::fromEntity).toList())
                .build();
    }
}
