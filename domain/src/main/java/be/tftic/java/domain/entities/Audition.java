package be.tftic.java.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "AUDITION")
@AllArgsConstructor
@NoArgsConstructor
public class Audition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE_AUDITION", nullable = false)
    private LocalDateTime dateAudition;

    @Column(name = "NUMERO_SALLE_AUDITION", nullable = false)
    private int numeroSalleAudition;

    @Column(name = "DEPOSITION_AUDITION", nullable = true)
    private String depositionAudition;

    @ManyToOne
    @JoinColumn(name = "CITOYEN_CONVOQUE_ID", nullable = false)
    private Personne convoque;

    @ManyToOne
    @JoinColumn(name = "AGENT_ID", nullable = false)
    private Personne agentTraitant;

    @ManyToOne
    @JoinColumn(name = "AVOCAT_ID", nullable = true)
    private Personne avocat;

    @ManyToOne
    @JoinColumn(name = "PLAINTE_ID", nullable = false)
    private Plainte plainte;

}
