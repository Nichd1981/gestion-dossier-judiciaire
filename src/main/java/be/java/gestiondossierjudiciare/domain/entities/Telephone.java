package be.java.gestiondossierjudiciare.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "CITOYEN_ID", nullable = false)
    private Citoyen citoyen;

    public Telephone(String numero, String libelle, Citoyen citoyen) {
        this.numero = numero;
        this.libelle = libelle;
        this.citoyen = citoyen;
    }
}
