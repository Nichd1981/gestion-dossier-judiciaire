package be.tftic.java.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "PLAINTE_DEPOSITION_HISTORIQUE")
@AllArgsConstructor
@NoArgsConstructor
public class PlainteDepositionHistorique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE_DEBUT", nullable = false)
    private LocalDateTime dateDebut;

    @Column(name = "DATE_FIN", nullable = true)
    private LocalDateTime dateFin;

    @Column(name = "DEPOSITION", nullable = false)
    private String deposition;

    @ManyToOne
    @JoinColumn(name = "PLAINTE_ID", nullable = false)
    private Plainte plainte;

}
