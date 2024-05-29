package be.tftic.java.domain.entities;

import be.tftic.java.domain.enums.JugementDecision;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "JUGEMENT")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Jugement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "DATE_JUDGMENT")
    @Setter
    private LocalDateTime dateJugement;

    @Enumerated(EnumType.STRING)
    @Column(name = "DECISION_JUDGMENT", nullable = true)
    @Setter
    private JugementDecision jugementDecision;

    @Column(name = "COMMENTAIRE_JUDGMENT", nullable = true)
    @Setter
    private String commentaire;

    @OneToOne
    @Setter
    @JoinColumn(name = "PLAINTE_ID", nullable = false)
    private Plainte plainte;

    public Jugement(LocalDateTime dateJugement, Plainte plainte) {
        this.dateJugement = dateJugement;
        this.plainte = plainte;
    }
}
