package be.tftic.java.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
/**
 * Classe représentant une déposition dans le cadre d'une plainte.
 *
 */
@Entity
@Table(name = "DEPOSITION")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Deposition {

    /**
     * Identifiant unique de la déposition.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Date de la déposition.
     */
    @Column(name = "DATE_DEPOSITION", nullable = false)
    private LocalDate dateDeposition;

    /**
     * Contenu de la déposition.
     */
    @Column(name = "DEPOSITION", nullable = false)
    private String deposition;

    /**
     * Plainte associée à la déposition.
     */
    @ManyToOne
    @JoinColumn(name = "COMPLAINT_ID", nullable = false)
    private Complaint complaint;

    /**
     * Constructeur de la classe `Déposition'.
     *
     * @param dateDeposition la date de la déposition
     * @param deposition     le contenu de la déposition
     * @param complaint       la plainte associée à la déposition
     */
    public Deposition(LocalDate dateDeposition, String deposition, Complaint complaint) {
        this.dateDeposition = dateDeposition;
        this.deposition = deposition;
        this.complaint = complaint;
    }
}
