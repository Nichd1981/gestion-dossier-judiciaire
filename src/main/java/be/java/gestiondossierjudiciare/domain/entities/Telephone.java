package be.java.gestiondossierjudiciare.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TELEPHONE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "NUMERO", nullable = false)
    private String numero;

    @Setter
    @Column(name = "LIBELLE", nullable = false)
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "PERSONNE_ID", nullable = false)
    private Personne personne;

    @Builder
    public Telephone(String numero, String libelle, Personne personne) {
        this.numero = numero;
        this.libelle = libelle;
        this.personne = personne;
    }
}
