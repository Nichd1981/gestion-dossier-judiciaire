package be.java.gestiondossierjudiciare.domain.entities;

import be.java.gestiondossierjudiciare.domain.enums.TypePlainte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "PLAINTE_TYPE_HISTORIQUE")
@AllArgsConstructor
@NoArgsConstructor
public class PlainteTypeHistorique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE_DEBUT", nullable = false)
    private LocalDateTime dateDebut;

    @Column(name = "DATE_FIN", nullable = true)
    private LocalDateTime dateFin;

    @Column(name = "TYPE_PLAINTE", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypePlainte typePlainte;

    @ManyToOne
    @JoinColumn(name = "PLAINTE_ID", nullable = false)
    private Plainte plainte;
}
