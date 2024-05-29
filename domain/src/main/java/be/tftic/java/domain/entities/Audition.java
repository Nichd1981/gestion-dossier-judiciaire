package be.tftic.java.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "AUDITION")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
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

    @Builder
    public Audition(LocalDateTime dateAudition, int numeroSalleAudition, String depositionAudition, Personne convoque, Personne agentTraitant, Personne avocat, Plainte plainte){
        this.dateAudition = dateAudition;
        this.numeroSalleAudition = numeroSalleAudition;
        this.depositionAudition = depositionAudition;
        this.convoque = convoque;
        this.agentTraitant = agentTraitant;
        this.avocat = avocat;
        this.plainte = plainte;
    }
}
