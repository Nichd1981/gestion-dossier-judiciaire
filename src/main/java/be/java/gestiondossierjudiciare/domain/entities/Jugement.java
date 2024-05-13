package be.java.gestiondossierjudiciare.domain.entities;

import be.java.gestiondossierjudiciare.domain.enums.JugementDecision;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "JUGEMENT")
public class Jugement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "DATE_JUDGMENT")
    private LocalDateTime dateJugement;

    @Enumerated(EnumType.STRING)
    @Column(name = "DECISION", nullable = true)
    private JugementDecision jugementDecision;

    @Column(name = "COMMENTAIRE", nullable = true)
    private String commentaire;

    @OneToOne
    @JoinColumn(name = "PLAINTE_ID", nullable = false)
    private Plainte plainte;

}
