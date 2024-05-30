package be.tftic.java.domain.entities;

import be.tftic.java.domain.enums.Genre;
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
@Table(name = "PERSONNE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Personne {

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
    @Column(name = "REGISTRE_NATIONAL", nullable = false, unique = true)
    private String registreNational;

    /**
     * Nom de la personne.
     *
     * Ce nom est obligatoire et ne peut pas être vide. Il peut être modifié à
     * tout moment.
     */
    @Setter
    @Column(name = "NOM", nullable = false)
    private String nom;

    /**
     * Prénom de la personne.
     *
     * Ce prénom est obligatoire et ne peut pas être vide. Il peut être modifié
     * à tout moment.
     */
    @Setter
    @Column(name = "PRENOM", nullable = false)
    private String prenom;

    /**
     * Date de naissance de la personne.
     *
     * Cette date est obligatoire et ne peut pas être dans le futur. Elle peut
     * être modifiée à tout moment.
     */
    @Setter
    @Column(name = "DATE_NAISSANCE", nullable = false)
    private LocalDateTime dateNaissance;

    /**
     * Lieu de naissance de la personne.
     *
     * Ce lieu est obligatoire et ne peut pas être vide. Il peut être modifié à
     * tout moment.
     */
    @Setter
    @Column(name = "LIEU_NAISSANCE", nullable = false)
    private String lieuNaissance;

    /**
     * Genre de la personne.
     *
     * Ce genre est obligatoire et peut prendre les valeurs suivantes : "HOMME",
     * "FEMME" ou "AUTRE". Il peut être modifié à tout moment.
     */
    @Setter
    @Column(name = "GENRE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    /**
     * Date de décès de la personne.
     *
     * Cette date est facultative et peut-être dans le futur. Elle peut être
     * modifiée à tout moment.
     */
    @Setter
    @Column(name = "DATE_DECES", nullable = true)
    private LocalDateTime dateDeces;

    /**
     * Photo de la personne.
     *
     * Cette photo est facultative et peut être modifiée à tout moment.
     */
    @Setter
    @Column(name = "PHOTO", nullable = true)
    private String photo;

    /**
     * Empreinte de la personne.
     *
     * Cette empreinte est facultative et peut être modifiée à tout moment.
     */
    @Setter
    @Column(name = "EMPREINTE", nullable = true)
    private String empreinte;

    /**
     * Avocat de la personne.
     *
     * Cet avocat est facultatif et peut être modifié à tout moment. Il est
     * représenté par une autre instance de la classe Personne.
     */
    @Setter
    @ManyToOne
    @JoinColumn(name = "AVOCAT_ID", nullable = true)
    private Personne avocat;

    /**
     * Adresses de la personne.
     *
     * Ces adresses sont facultatives et peuvent être modifiées à tout moment.
     * Elles sont représentées par des instances de la classe Adresse.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Adresse> adresses = new HashSet<>();

    /**
     * Téléphones de la personne.
     *
     * Ces téléphones sont facultatifs et peuvent être modifiés à tout moment.
     * Ils sont représentés par des instances de la classe Telephone.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Telephone> telephones = new HashSet<>();

    /**
     * Constructeur de la classe Personne.
     *
     * Ce constructeur permet de créer une nouvelle instance de la classe
     * Personne en définissant son numéro de registre national, son
     * nom, son prénom, sa date de naissance, son lieu de naissance, son genre,
     * sa date de décès, sa photo et son empreinte.
     *
     * @param registreNational le numéro de registre national de la personne
     * @param nom              le nom de la personne
     * @param prenom           le prénom de la personne
     * @param dateNaissance    la date de naissance de la personne
     * @param lieuNaissance    le lieu de naissance de la personne
     * @param genre            le genre de la personne
     * @param dateDeces        la date de décès de la personne
     * @param photo            la photo de la personne
     * @param empreinte        l'empreinte de la personne
     * @see Genre
     */
    @Builder
    public Personne(String registreNational, String nom, String prenom, LocalDateTime dateNaissance, String lieuNaissance, Genre genre, LocalDateTime dateDeces, String photo, String empreinte) {
        this.registreNational = registreNational;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.genre = genre;
        this.dateDeces = dateDeces;
        this.photo = photo;
        this.empreinte = empreinte;
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
        Personne personne = (Personne) o;
        return Objects.equals(registreNational, personne.registreNational);
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
        return Objects.hashCode(registreNational);
    }
}