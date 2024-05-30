package be.tftic.java.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Classe représentant une audition dans l'application.
 *
 * @Entity indique que cette classe est une entité JPA, c'est-à-dire qu'elle est mappée sur une table dans la base de données.
 * @Table(name = "AUDITION") indique que la table associée à cette entité dans la base de données s'appelle "AUDITION".
 * @AllArgsConstructor indique que Lombok génère un constructeur avec tous les attributs de la classe.
 * @NoArgsConstructor indique que Lombok génère un constructeur sans argument.
 * @Getter indique que Lombok génère des méthodes getter pour tous les attributs de la classe.
 * @Setter indique que Lombok génère des méthodes setter pour tous les attributs de la classe.
 */
@Entity
@Table(name = "AUDITION")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Audition {

    /**
     * L'identifiant unique de l'audition.
     *
     * @Id indique que cet attribut est la clé primaire de l'entité.
     * @GeneratedValue(strategy = GenerationType.IDENTITY) indique que la valeur de l'identifiant est générée automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * La date et l'heure de l'audition.
     *
     * @Column(name = "DATE_AUDITION", nullable = false) indique que cet attribut est mappé sur la colonne "DATE_AUDITION" dans la table de la base de données et qu'il ne peut pas être null.
     */
    @Column(name = "DATE_AUDITION", nullable = false)
    private LocalDateTime dateAudition;

    /**
     * Le numéro de la salle d'audition.
     *
     * @Column(name = "NUMERO_SALLE_AUDITION", nullable = false) indique que cet attribut est mappé sur la colonne "NUMERO_SALLE_AUDITION" dans la table de la base de données et qu'il ne peut pas être null.
     */
    @Column(name = "ROOM_NUMBER_AUDITION", nullable = false)
    private String roomNumberAudition;

    /**
     * La déposition faite lors de l'audition, si elle a eu lieu.
     *
     * @Column(name = "DEPOSITION_AUDITION", nullable = true) indique que cet attribut est mappé sur la colonne "DEPOSITION_AUDITION" dans la table de la base de données et qu'il peut être null.
     */
    @Column(name = "DEPOSITION_AUDITION")
    private String depositionAudition;

    /**
     * La personne convoquée pour l'audition.
     *
     * @ManyToOne indique que cet attribut représente une association de type "many-to-one" entre l'entité Audition et l'entité Personne, c'est-à-dire qu'une personne peut être convoquée pour plusieurs auditions mais qu'une audition ne concerne qu'une seule personne.
     * @JoinColumn(name = "CITOYEN_CONVOQUE_ID", nullable = false) indique que cette association est mappée sur la colonne "CITOYEN_CONVOQUE_ID" dans la table de la base de données et qu'elle ne peut pas être null.
     */
    @ManyToOne
    @JoinColumn(name = "CITIZEN_ID", nullable = false)
    private Person citizen;

    /**
     * L'agent traitant l'audition.
     *
     * @ManyToOne indique que cet attribut représente une association de type "many-to-one" entre l'entité Audition et l'entité Personne, c'est-à-dire qu'un agent peut traiter plusieurs auditions mais qu'une audition ne concerne qu'un seul agent.
     * @JoinColumn(name = "AGENT_ID", nullable = false) indique que cette association est mappée sur la colonne "AGENT_ID" dans la table de la base de données et qu'elle ne peut pas être null.
     */
    @ManyToOne
    @JoinColumn(name = "AGENT_ID", nullable = false)
    private Person agent;

    /**
     * L'avocat de la personne convoquée, si elle en a un.
     *
     * @ManyToOne indique que cet attribut représente une association de type "many-to-one" entre l'entité Audition et l'entité Personne, c'est-à-dire qu'un avocat peut représenter plusieurs personnes lors d'auditions mais qu'une audition ne concerne qu'un seul avocat.
     * @JoinColumn(name = "AVOCAT_ID", nullable = true) indique que cette association est mappée sur la colonne "AVOCAT_ID" dans la table de la base de données et qu'elle peut être null.
     */
    @ManyToOne
    @JoinColumn(name = "LAWYER_ID")
    private Person lawyer;

    /**
     * La plainte associée à l'audition.
     *
     * @ManyToOne indique que cet attribut représente une association de type "many-to-one" entre l'entité Audition et l'entité Plainte, c'est-à-dire qu'une plainte peut concerner plusieurs auditions mais qu'une audition ne concerne qu'une seule plainte.
     * @JoinColumn(name = "PLAINTE_ID", nullable = false) indique que cette association est mappée sur la colonne "PLAINTE_ID" dans la table de la base de données et qu'elle ne peut pas être null.
     */
    @ManyToOne
    @JoinColumn(name = "COMPLAINT_ID", nullable = false)
    private Complaint complaint;

    /**
     * Constructeur de l'entité Audition avec les attributs dateAudition, numeroSalleAudition, depositionAudition, convoque, agentTraitant, avocat et plainte.
     *
     * @param dateAudition la date et l'heure de l'audition.
     * @param roomNumberAudition le numéro de la salle d'audition.
     * @param depositionAudition la déposition faite lors de l'audition, si elle a eu lieu.
     * @param citizen la personne convoquée pour l'audition.
     * @param agent l'agent traitant l'audition.
     * @param lawyer l'avocat de la personne convoquée, si elle en a un.
     * @param complaint la plainte associée à l'audition.
     */
    @Builder
    public Audition(LocalDateTime dateAudition, String roomNumberAudition, String depositionAudition,
                    Person citizen, Person agent, Person lawyer, Complaint complaint){
        this.dateAudition = dateAudition;
        this.roomNumberAudition = roomNumberAudition;
        this.depositionAudition = depositionAudition;
        this.citizen = citizen;
        this.agent = agent;
        this.lawyer = lawyer;
        this.complaint = complaint;
    }
}
