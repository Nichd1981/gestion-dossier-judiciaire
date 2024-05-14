package be.java.gestiondossierjudiciare.domain.entities;

import be.java.gestiondossierjudiciare.domain.enums.JugementDecision;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "JUGEMENT")
@AllArgsConstructor
@NoArgsConstructor
public class Jugement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "DATE_JUDGMENT")
    private LocalDateTime dateJugement;

    @Enumerated(EnumType.STRING)
    @Column(name = "DECISION_JUDGMENT", nullable = true)
    private JugementDecision jugementDecision;

    @Column(name = "COMMENTAIRE_JUDGMENT", nullable = true)
    private String commentaire;

    @OneToOne
    @JoinColumn(name = "PLAINTE_ID", nullable = false)
    private Plainte plainte;

}
