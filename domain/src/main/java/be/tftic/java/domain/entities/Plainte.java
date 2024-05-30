package be.tftic.java.domain.entities;

import be.tftic.java.domain.enums.Statut;
import be.tftic.java.domain.enums.TypePlainte;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Classe représentant une plainte.
 *
 * Cette classe permet de stocker les informations relatives à une plainte,
 * telles que son numéro de dossier, son statut, son type, sa date, son
 * jugement, son plaignant, son agent traitant, les personnes concernées,
 * les auditions et les dépositions.
 *
 */
@Entity
@Table(name = "PLAINTE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Plainte {

    /**
     * Identifiant unique de la plainte.
     *
     * Cet identifiant est généré automatiquement par la base de données lorsque
     * la plainte est créée.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Numéro de dossier de la plainte.
     *
     * Ce numéro est unique pour chaque plainte et permet de l'identifier de
     * manière certaine. Il est obligatoire et ne peut pas être modifié une
     * fois qu'il a été défini.
     */
    @Column(name = "NUMERO_DOSSIER", nullable = false, unique = true)
    private String numeroDossier;

    /**
     * Statut de la plainte.
     *
     * Ce statut peut prendre les valeurs suivantes : "ENREGISTREE", "EN COURS
     * DE TRAITEMENT", "CLASSEE SANS SUITE", "RENVOYEE devant le tribunal". Il
     * est obligatoire et peut être modifié à tout moment.
     */
    @Column(name = "STATUT", nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private Statut statut = Statut.ENREGISTREE;

    /**
     * Type de la plainte.
     *
     * Ce type est facultatif et peut être défini pour préciser la nature de la
     * plainte venant de l'Enum (par exemple : "Sans_suite", "Contravention", "Delit", "Crime"). Il peut
     * être modifié à tout moment.
     */
    @Column(name = "TYPE_PLAINTE", nullable = true)
    @Enumerated(EnumType.STRING)
    @Setter
    private TypePlainte typePlainte;

    /**
     * Date de la plainte.
     *
     * Cette date correspond à la date à laquelle la plainte a été déposée.
     * Elle est obligatoire et ne peut pas être modifiée une fois qu'elle a été
     * définie.
     */
    @Column(name = "DATE_PLAINTE")
    private LocalDateTime datePlainte;

    /**
     * Jugement associé à la plainte.
     *
     * Ce jugement est facultatif et peut être défini pour stocker les
     * informations relatives au jugement rendu suite à la plainte
     * (par exemple : "Sans_suite", "Condamnation"). Il peut être
     * modifié à tout moment.
     */
    @OneToOne
    @JoinColumn(name = "JUGEMENT_ID", nullable = true)
    private Jugement jugement;

    /**
     * Plaignant de la plainte.
     *
     * Le plaignant est obligatoire et correspond à la personne qui a déposé
     * la plainte.
     */
    @ManyToOne
    @Setter
    @JoinColumn(name = "PLAIGNANT_ID")
    private Personne plaignant;
    /**
     * Agent traitant de la plainte.
     *
     * L'agent est obligatoire et correspond à la personne en charge de
     * traiter la plainte.
     */
    @ManyToOne
    @Setter
    @JoinColumn(name = "AGENT_TRAITANT_ID")
    private Personne agentTraitant;

    /**
     * Personnes concernées par la plainte.
     *
     * Ces personnes sont facultatives et peuvent être définies pour stocker les
     * informations relatives aux personnes concernées par la plainte.
     */
    @ManyToMany
    @JoinTable(name = "PLAINTE_PERSONNE",
            joinColumns = @JoinColumn(name = "PLAINTE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERSONNE_ID"))
    private Set<Personne> personnesConcernees = new HashSet<>();

    /**
     * Auditions associées à la plainte.
     *
     * Ces auditions sont facultatives et peuvent être définies pour stocker les
     * informations relatives aux auditions réalisées dans le cadre de la
     * plainte (par exemple : la date de l'audition, la personne auditionnée,
     * le compte-rendu, etc.).
     */
    @OneToMany(mappedBy = "plainte", cascade = CascadeType.ALL)
    private Set<Audition> auditions = new HashSet<>();

    /**
     * Dépôts de plainte associés à la plainte.
     *
     * Ces dépôts de plainte sont facultatifs et peuvent être définis pour stocker
     * les informations relatives aux dépôts de plainte réalisés dans le cadre
     * de la plainte (par exemple : la date du dépôt de plainte, la personne
     * ayant déposé plainte, le compte-rendu, etc.). Ils sont représentés par des
     * instances de la classe Deposition.
     */
    @OneToMany(mappedBy = "plainte", cascade = CascadeType.ALL)
    private Set<Deposition> depositions = new HashSet<>();

    /**
     * Renvoie true si la plainte est égale à l'objet en paramètre, false sinon.
     *
     * Deux instances de la classe Plainte sont égales si elles ont
     * le même identifiant.
     *
     * @param o l'objet à comparer à la plainte
     * @return true si la plainte est égale à l'objet en paramètre, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plainte plainte = (Plainte) o;
        return Objects.equals(id, plainte.id);
    }

    /**
     * Renvoie le code de hachage de la plainte.
     *
     * Le code de hachage est calculé à partir de l'identifiant de la plainte.
     *
     * @return le code de hachage de la plainte
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Constructeur de la classe Plainte.
     *
     * Ce constructeur permet de créer une nouvelle instance de la classe
     * Plainte en définissant son numéro de dossier, son statut,
     * sa date, son plaignant et son agent traitant.
     *
     * @param numeroDossier le numéro de dossier de la plainte
     * @param statut        le statut de la plainte
     * @param datePlainte   la date de la plainte
     * @param plaignant     le plaignant de la plainte
     * @param agentTraitant l'agent traitant de la plainte
     */
    @Builder
    public Plainte(String numeroDossier, Statut statut, LocalDateTime datePlainte, Personne plaignant, Personne agentTraitant) {
        this.numeroDossier = numeroDossier;
        this.statut = statut;
        this.datePlainte = datePlainte;
        this.plaignant = plaignant;
        this.agentTraitant = agentTraitant;
    }

}
