package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Personne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class PersonneShortDTO {

    String registreNational;
    String nom;
    String prenom;
    LocalDateTime dateNaissance;
    String lieuNaissance;
    String genre;

    public static PersonneShortDTO fromEntity(Personne personne){
        return PersonneShortDTO.builder()
                .registreNational(personne.getRegistreNational())
                .nom(personne.getNom())
                .prenom(personne.getPrenom())
                .dateNaissance(personne.getDateNaissance())
                .lieuNaissance(personne.getLieuNaissance())
                .genre(personne.getGenre().toString())
                .build();
    }

}
