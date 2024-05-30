package be.tftic.java.domain.entities;

import be.tftic.java.domain.enums.JudgmentDecision;
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
@Table(name = "JUDGMENT")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Judgment {

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
    private LocalDateTime judgmentDate;

    /**
     * Décision du jugement.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "JUDGMENT_DECISION")
    @Setter
    private JudgmentDecision judgmentDecision;

    /**
     * Commentaire associé au jugement.
     */
    @Column(name = "JUDGMENT_COMMENTARY")
    @Setter
    private String commentary;

    /**
     * Plainte associée au jugement.
     */
    @OneToOne
    @Setter
    @JoinColumn(name = "COMPLAINT_ID", nullable = false)
    private Complaint complaint;

    /**
     * Constructeur de la classe Jugement.
     *
     * @param judgmentDate la date du jugement
     * @param complaint      la plainte associée au jugement
     *
     * @see Complaint
     */
    public Judgment(LocalDateTime judgmentDate, Complaint complaint) {
        this.judgmentDate = judgmentDate;
        this.complaint = complaint;
    }
}
