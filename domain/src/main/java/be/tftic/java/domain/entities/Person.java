package be.tftic.java.domain.entities;

import be.tftic.java.domain.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe représentant une personne.
 *
 * Cette classe permet de stocker les informations relatives à une personne,
 * telles que son numéro de registre national, son nom, son prénom, sa date de
 * naissance, son lieu de naissance, son genre, sa date de décès, sa photo,
 * son empreinte, son avocat, ses adresses et ses téléphones.
 *
 */
@Entity
@Table(name = "PERSON")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Person {

    /**
     * Identifiant unique de la personne.
     *
     * Cet identifiant est généré automatiquement par la base de données lorsque
     * la personne est créée.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Numéro de registre national de la personne.
     *
     * Ce numéro est unique pour chaque personne et permet de l'identifier de
     * manière certaine. Il est obligatoire et ne peut pas être modifié une
     * fois qu'il a été défini.
     */
    @Column(name = "NATIONAL_REGISTER", nullable = false, unique = true)
    private String nationalRegister;

    /**
     * Nom de la personne.
     *
     * Ce nom est obligatoire et ne peut pas être vide. Il peut être modifié à
     * tout moment.
     */
    @Setter
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * Prénom de la personne.
     *
     * Ce prénom est obligatoire et ne peut pas être vide. Il peut être modifié
     * à tout moment.
     */
    @Setter
    @Column(name = "FIRSTNAME", nullable = false)
    private String firstname;

    /**
     * Date de naissance de la personne.
     *
     * Cette date est obligatoire et ne peut pas être dans le futur. Elle peut
     * être modifiée à tout moment.
     */
    @Setter
    @Column(name = "BIRTHDATE", nullable = false)
    private LocalDateTime birthdate;

    /**
     * Lieu de naissance de la personne.
     *
     * Ce lieu est obligatoire et ne peut pas être vide. Il peut être modifié à
     * tout moment.
     */
    @Setter
    @Column(name = "BIRTHPLACE", nullable = false)
    private String birthplace;

    /**
     * Genre de la personne.
     *
     * Ce genre est obligatoire et peut prendre les valeurs suivantes : "HOMME",
     * "FEMME" ou "AUTRE". Il peut être modifié à tout moment.
     */
    @Setter
    @Column(name = "GENDER", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * Date de décès de la personne.
     *
     * Cette date est facultative et peut-être dans le futur. Elle peut être
     * modifiée à tout moment.
     */
    @Setter
    @Column(name = "DEATH_DATE")
    private LocalDateTime deathDate;

    /**
     * Photo de la personne.
     *
     * Cette photo est facultative et peut être modifiée à tout moment.
     */
    @Setter
    @Column(name = "PICTURE")
    private String picture;

    /**
     * Empreinte de la personne.
     *
     * Cette empreinte est facultative et peut être modifiée à tout moment.
     */
    @Setter
    @Column(name = "IMPRINT", nullable = true)
    private String imprint;

    /**
     * Avocat de la personne.
     *
     * Cet avocat est facultatif et peut être modifié à tout moment. Il est
     * représenté par une autre instance de la classe Personne.
     */
    @Setter
    @ManyToOne
    @JoinColumn(name = "LAWYER_ID")
    private Person lawyer;

    /**
     * Adresses de la personne.
     *
     * Ces adresses sont facultatives et peuvent être modifiées à tout moment.
     * Elles sont représentées par des instances de la classe Adresse.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Address> address = new HashSet<>();

    /**
     * Téléphones de la personne.
     *
     * Ces téléphones sont facultatifs et peuvent être modifiés à tout moment.
     * Ils sont représentés par des instances de la classe Telephone.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Phone> phones = new HashSet<>();

    /**
     * Constructeur de la classe Personne.
     *
     * Ce constructeur permet de créer une nouvelle instance de la classe
     * Personne en définissant son numéro de registre national, son
     * nom, son prénom, sa date de naissance, son lieu de naissance, son genre,
     * sa date de décès, sa photo et son empreinte.
     *
     * @param nationalRegister le numéro de registre national de la personne
     * @param name              le nom de la personne
     * @param firstname           le prénom de la personne
     * @param birthdate    la date de naissance de la personne
     * @param birthplace    le lieu de naissance de la personne
     * @param gender            le genre de la personne
     * @param deathDate        la date de décès de la personne
     * @param picture            la photo de la personne
     * @param imprint        l'empreinte de la personne
     * @see Gender
     */
    @Builder
    public Person(String nationalRegister, String name, String firstname, LocalDateTime birthdate,
                  String birthplace, Gender gender, LocalDateTime deathDate, String picture, String imprint) {
        this.nationalRegister = nationalRegister;
        this.name = name;
        this.firstname = firstname;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.gender = gender;
        this.deathDate = deathDate;
        this.picture = picture;
        this.imprint = imprint;
    }

    /**
     * Renvoie true si la personne est égale à l'objet en paramètre,
     * false sinon.
     *
     * Deux instances de la classe Personne sont égales si elles ont
     * le même numéro de registre national.
     *
     * @param o l'objet à comparer à la personne
     * @return true si la personne est égale à l'objet en paramètre, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(nationalRegister, person.nationalRegister);
    }

    /**
     * Renvoie le code de hachage de la personne.
     *
     * Le code de hachage est calculé à partir du numéro de registre national de
     * la personne.
     *
     * @return le code de hachage de la personne
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(nationalRegister);
    }
}