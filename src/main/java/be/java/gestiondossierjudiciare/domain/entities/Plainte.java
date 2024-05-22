package be.java.gestiondossierjudiciare.domain.entities;

import be.java.gestiondossierjudiciare.domain.enums.Statut;
import be.java.gestiondossierjudiciare.domain.enums.TypePlainte;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PLAINTE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Plainte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMERO_DOSSIER", nullable = false, unique = true)
    private String numeroDossier;

    @Column(name = "STATUT", nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private Statut statut = Statut.ENREGISTREE;

    @Column(name = "TYPE_PLAINTE", nullable = true)
    @Enumerated(EnumType.STRING)
    private TypePlainte typePlainte;

    @Column(name = "DATE_PLAINTE")
    private LocalDateTime datePlainte;

    @OneToOne
    @JoinColumn(name = "JUGEMENT_ID", nullable = true)
    private Jugement jugement;

    @ManyToOne
    @Setter
    @JoinColumn(name = "PLAIGNANT_ID")
    private Personne plaignant;

    @ManyToOne
    @Setter
    @JoinColumn(name = "AGENT_TRAITANT_ID")
    private Personne agentTraitant;

    @ManyToMany
    @JoinTable(name = "PLAINTE_PERSONNE",
                joinColumns = @JoinColumn(name = "PLAINTE_ID"),
    inverseJoinColumns = @JoinColumn(name = "PERSONNE_ID"))
    private Set<Personne> personnesConcernees = new HashSet<>();

    @OneToMany(mappedBy = "plainte", cascade = CascadeType.ALL)
    private Set<Audition> auditions = new HashSet<>();

    @OneToMany(mappedBy = "plainte", cascade = CascadeType.ALL)
    private Set<PlainteDepositionHistorique> depositions = new HashSet<>();

    @Builder
    public Plainte(String numeroDossier, Statut statut, LocalDateTime datePlainte, Personne plaignant, Personne agentTraitant) {
        this.numeroDossier = numeroDossier;
        this.statut = statut;
        this.datePlainte = datePlainte;
        this.plaignant = plaignant;
        this.agentTraitant = agentTraitant;
    }
}
