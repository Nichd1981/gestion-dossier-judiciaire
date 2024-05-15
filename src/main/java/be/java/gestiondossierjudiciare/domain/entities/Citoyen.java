package be.java.gestiondossierjudiciare.domain.entities;

import be.java.gestiondossierjudiciare.domain.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CITOYEN")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Citoyen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REGISTRE_NATIONAL", nullable = false, unique = true)
    private String registreNational;

    @Setter
    @Column(name = "NOM", nullable = false)
    private String nom;

    @Setter
    @Column(name = "PRENOM", nullable = false)
    private String prenom;

    @Setter
    @Column(name = "DATE_NAISSANCE", nullable = false)
    private LocalDateTime dateNaissance;

    @Setter
    @Column(name = "LIEU_NAISSANCE", nullable = false)
    private String lieuNaissance;

    @Setter
    @Column(name = "GENRE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Setter
    @Column(name = "DATE_DECES", nullable = true)
    private LocalDateTime dateDeces;

    @Setter
    @Column(name = "PHOTO", nullable = true)
    private String photo;

    @Setter
    @Column(name="EMPREINTE", nullable = true)
    private String empreinte;

    @Setter
    @ManyToOne
    @JoinColumn(name = "AVOCAT_ID", nullable = true)
    private Citoyen avocat;

    @OneToMany
    private Set<Adresse> adresses = new HashSet<>();

    @OneToMany
    private Set<Telephone> telephones = new HashSet<>();

    public Citoyen(String registreNational, String nom, String prenom, LocalDateTime dateNaissance, String lieuNaissance, Genre genre, LocalDateTime dateDeces, String photo, String empreinte) {
        this.registreNational = registreNational;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.genre = genre;
        this.dateDeces = dateDeces;
        this.photo = photo;
        this.empreinte = empreinte;
    }
}
