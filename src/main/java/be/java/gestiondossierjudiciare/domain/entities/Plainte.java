package be.java.gestiondossierjudiciare.domain.entities;

import be.java.gestiondossierjudiciare.domain.enums.TypePlainte;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "PLAINTE")
public class Plainte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE_PLAINTE")
    private LocalDateTime datePlainte;

    @Column(name = "NUMERO_PLAINTE")
    private String numeroPlainte;

    @Column(name = "DEPOSITION")
    private String deposition;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_PLAINTE")
    private TypePlainte typePlainte = TypePlainte.ENREGISTREE;

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

    @OneToOne
    @JoinColumn(name = "JUGEMENT_ID", nullable = true)
    private Jugement jugement;

    @OneToMany(mappedBy = "plainte")
    private Set<Audition> auditions;

}
