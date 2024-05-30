package be.tftic.java.domain.entities;

import be.tftic.java.domain.enums.JugementDecision;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
/**
 * Classe représentant un jugement dans le cadre d'une plainte.
 *
 * @author [Votre nom]
 * @version 1.0
 */
@Entity
@Table(name = "JUGEMENT")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Jugement {

    /**
     * Identifiant unique du jugement.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Date du jugement.
     */
    @Column(name= "DATE_JUDGMENT")
    @Setter
    private LocalDateTime dateJugement;

    /**
     * Décision du jugement.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "DECISION_JUDGMENT", nullable = true)
    @Setter
    private JugementDecision jugementDecision;

    /**
     * Commentaire associé au jugement.
     */
    @Column(name = "COMMENTAIRE_JUDGMENT", nullable = true)
    @Setter
    private String commentaire;

    /**
     * Plainte associée au jugement.
     */
    @OneToOne
    @Setter
    @JoinColumn(name = "PLAINTE_ID", nullable = false)
    private Plainte plainte;

    /**
     * Constructeur de la classe Jugement.
     *
     * @param dateJugement la date du jugement
     * @param plainte      la plainte associée au jugement
     *
     * @see Plainte
     */
    public Jugement(LocalDateTime dateJugement, Plainte plainte) {
        this.dateJugement = dateJugement;
        this.plainte = plainte;
    }
}
