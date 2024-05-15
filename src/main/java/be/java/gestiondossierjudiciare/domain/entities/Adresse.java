package be.java.gestiondossierjudiciare.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ADRESSE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "RUE", nullable = false)
    private String rue;

    @Setter
    @Column(name = "NUMERO",nullable = false)
    private String numero;

    @Setter
    @Column(name = "VILLE", nullable = false)
    private String ville;

    @Setter
    @Column(name = "CODE_POSTAL", nullable = false)
    private String codePostal;

    @Setter
    @Column(name = "PAYS", nullable = false)
    private String pays;

    @Setter
    @Column(name = "TYPE", nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "CITOYEN_ID", nullable = false)
    private Citoyen citoyen;

    public Adresse(String rue, String numero, String ville, String codePostal, String pays, String type, Citoyen citoyen) {
        this.rue = rue;
        this.numero = numero;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
        this.type = type;
        this.citoyen = citoyen;
    }
}
