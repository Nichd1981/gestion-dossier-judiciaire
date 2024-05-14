package be.java.gestiondossierjudiciare.domain.entities;

import be.java.gestiondossierjudiciare.domain.enums.Statut;
import be.java.gestiondossierjudiciare.domain.enums.TypePlainte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "PLAINTE")
@AllArgsConstructor
@NoArgsConstructor
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
    private Citoyen plaignant;

    @ManyToOne
    @JoinColumn(name = "AGENT_TRAITANT_ID")
    private Citoyen agentTraitant;

    @ManyToMany
    @JoinTable(name = "PLAINTE_CITOYEN",
                joinColumns = @JoinColumn(name = "PLAINTE_ID"),
                inverseJoinColumns = @JoinColumn(name = "CITOYEN_ID"))
    private Set<Citoyen> citoyensConcernes;

    @OneToMany(mappedBy = "plainte")
    private Set<Audition> auditions;

}
