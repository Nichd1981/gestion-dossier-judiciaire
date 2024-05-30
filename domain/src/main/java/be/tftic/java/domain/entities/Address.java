package be.tftic.java.domain.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Classe représentant une adresse dans l'application.
 *
 * @Entity indique que cette classe est une entité JPA, c'est-à-dire qu'elle est mappée sur une table dans la base de données.
 * @Table(name = "ADRESSE") indique que la table associée à cette entité dans la base de données s'appelle "ADRESSE".
 * @AllArgsConstructor indique que Lombok génère un constructeur avec tous les attributs de la classe.
 * @NoArgsConstructor indique que Lombok génère un constructeur sans argument.
 * @Getter indique que Lombok génère des méthodes getter pour tous les attributs de la classe.
 */
@Entity
@Table(name = "ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Address {

    /**
     * L'identifiant unique de l'adresse.
     *
     * @Id indique que cet attribut est la clé primaire de l'entité.
     * @GeneratedValue(strategy = GenerationType.IDENTITY) indique que la valeur de l'identifiant est générée automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * La rue de l'adresse.
     *
     * @Setter indique que Lombok génère une méthode setter pour cet attribut.
     * @Column(name = "RUE", nullable = false) indique que cet attribut est mappé sur la colonne "RUE" dans la table de la base de données et qu'il ne peut pas être null.
     */
    @Setter
    @Column(name = "STREET", nullable = false)
    private String street;

    /**
     * Le numéro de l'adresse.
     *
     * @Setter indique que Lombok génère une méthode setter pour cet attribut.
     * @Column(name = "NUMERO", nullable = false) indique que cet attribut est mappé sur la colonne "NUMERO" dans la table de la base de données et qu'il ne peut pas être null.
     */
    @Setter
    @Column(name = "NUMBER",nullable = false)
    private String number;

    /**
     * La ville de l'adresse.
     *
     * @Setter indique que Lombok génère une méthode setter pour cet attribut.
     * @Column(name = "VILLE", nullable = false) indique que cet attribut est mappé sur la colonne "VILLE" dans la table de la base de données et qu'il ne peut pas être null.
     */
    @Setter
    @Column(name = "CITY", nullable = false)
    private String city;

    /**
     * Le code postal de l'adresse.
     *
     * @Setter indique que Lombok génère une méthode setter pour cet attribut.
     * @Column(name = "CODE_POSTAL", nullable = false) indique que cet attribut est mappé sur la colonne "CODE_POSTAL" dans la table de la base de données et qu'il ne peut pas être null.
     */

    @Setter
    @Column(name = "POSTCODE", nullable = false)
    private String postcode;

    /**
     * Le pays de l'adresse.
     *
     * @Setter indique que Lombok génère une méthode setter pour cet attribut.
     * @Column(name = "PAYS", nullable = false) indique que cet attribut est mappé sur la colonne "PAYS" dans la table de la base de données et qu'il ne peut pas être null.
     */
    @Setter
    @Column(name = "COUNTRY", nullable = false)
    private String country;

    /**
     * Le libellé de l'adresse, c'est-à-dire une représentation sous forme de chaîne de caractères de l'adresse complète.
     *
     * @Setter indique que Lombok génère une méthode setter pour cet attribut.
     * @Column(name = "LIBELLE", nullable = false) indique que cet attribut est mappé sur la colonne "LIBELLE" dans la table de la base de données et qu'il ne peut pas être null.
     */
    @Setter
    @Column(name = "LABEL", nullable = false)
    private String label;

    /**
     * La personne associée à l'adresse.
     *
     * @ManyToOne indique que cet attribut représente une association de type "many-to-one" entre l'entité Adresse et l'entité Personne, c'est-à-dire qu'une personne peut avoir plusieurs adresses mais qu'une adresse n'est associée qu'à une seule personne.
     * @JoinColumn(name = "PERSONNE_ID", nullable = false) indique que cette association est mappée sur la colonne "PERSONNE_ID" dans la table de la base de données et qu'elle ne peut pas être null.
     */
    @ManyToOne
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person person;

    /**
     * Constructeur de l'entité Adresse avec les attributs rue, numero, ville, codePostal, pays, libelle et personne.
     *
     * @param street la rue de l'adresse.
     * @param number le numéro de l'adresse.
     * @param city la ville de l'adresse.
     * @param postcode le code postal de l'adresse.
     * @param country le pays de l'adresse.
     * @param label le libellé de l'adresse.
     * @param person la personne associée à l'adresse.
     */
    @Builder
    public Address(String street, String number, String city, String postcode, String country,
                   String label, Person person) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
        this.label = label;
        this.person = person;
    }

}
