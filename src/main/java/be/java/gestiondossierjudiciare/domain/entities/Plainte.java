package be.java.gestiondossierjudiciare.domain.entities;

import be.java.gestiondossierjudiciare.domain.enums.Statut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Statut statut = Statut.ENREGISTREE;

    @Column(name = "DATE_PLAINTE")
    private LocalDateTime datePlainte;

    @OneToOne
    @JoinColumn(name = "JUGEMENT_ID", nullable = true)
    private Jugement jugement;

    @ManyToOne
    @JoinColumn(name = "PLAIGNANT_ID")
    private Personne plaignant;

    @ManyToOne
    @JoinColumn(name = "AGENT_TRAITANT_ID")
    private Personne agentTraitant;

    @ManyToMany
    @JoinTable(name = "PLAINTE_PERSONNE",
                joinColumns = @JoinColumn(name = "PLAINTE_ID"),
    inverseJoinColumns = @JoinColumn(name = "PERSONNE_ID"))
    private Set<Personne> personnesConcernees = new HashSet<>();

    @OneToMany(mappedBy = "plainte", fetch = FetchType.EAGER)
    private Set<Audition> auditions = new HashSet<>();

    @OneToMany(mappedBy = "plainte", fetch = FetchType.EAGER)
    private Set<PlainteDepositionHistorique> depositions = new HashSet<>();

    @OneToMany(mappedBy = "plainte", fetch = FetchType.EAGER)
    private Set<PlainteTypeHistorique> types = new HashSet<>();

    public Plainte(String numeroDossier, Statut statut, LocalDateTime datePlainte, Personne plaignant, Personne agentTraitant) {
        this.numeroDossier = numeroDossier;
        this.statut = statut;
        this.datePlainte = datePlainte;
        this.plaignant = plaignant;
        this.agentTraitant = agentTraitant;
    }
}
