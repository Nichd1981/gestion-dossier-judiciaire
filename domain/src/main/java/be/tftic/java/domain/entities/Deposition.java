package be.tftic.java.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "DEPOSITION")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Deposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE_DEPOSITION", nullable = false)
    private LocalDate dateDeposition;

    @Column(name = "DEPOSITION", nullable = false)
    private String deposition;

    @ManyToOne
    @JoinColumn(name = "PLAINTE_ID", nullable = false)
    private Plainte plainte;

    public Deposition(LocalDate dateDeposition, String deposition, Plainte plainte) {
        this.dateDeposition = dateDeposition;
        this.deposition = deposition;
        this.plainte = plainte;
    }
}
