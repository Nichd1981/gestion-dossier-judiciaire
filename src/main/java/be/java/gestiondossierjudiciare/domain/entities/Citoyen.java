package be.java.gestiondossierjudiciare.domain.entities;

import be.java.gestiondossierjudiciare.domain.enums.Genre;
import be.java.gestiondossierjudiciare.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CITOYEN")
@AllArgsConstructor
@NoArgsConstructor
public class Citoyen {

    @Id
    @Column(name = "REGISTRE_NATIONAL", nullable = false, unique = true)
    private String registreNational;

    @Column(name = "NOM", nullable = false)
    private String nom;

    @Column(name = "PRENOM", nullable = false)
    private String prenom;

    @Column(name = "DATE_NAISSANCE", nullable = false)
    private LocalDateTime dateNaissance;

    @Column(name = "LIEU_NAISSANCE", nullable = false)
    private String lieuNaissance;

    @Column(name = "GENRE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "DATE_DECES", nullable = true)
    private LocalDateTime dateDeces;

    @Column(name = "PHOTO", nullable = true)
    private String photo;

    @Column(name="EMPREINTE", nullable = true)
    private String empreinte;

    @ManyToOne
    @JoinColumn(name = "AVOCAT_ID", nullable = true)
    private Citoyen avocat;

    @OneToMany(mappedBy = "citoyen")
    private Set<Adresse> adresses;

    @OneToMany(mappedBy = "citoyen")
    private Set<Telephone> telephones;

}
