package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Personne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class PersonneDetailResponse {

    String registreNational;
    String nom;
    String prenom;
    LocalDateTime dateNaissance;
    String lieuNaissance;
    String genre;
    LocalDateTime dateDeces;
    String photo;
    String empreinte;
    List<AdresseResponse> adresses;
    List<TelephoneResponse> telephones;

    public static PersonneDetailResponse fromEntity(Personne personne){
        return PersonneDetailResponse.builder()
                .registreNational(personne.getRegistreNational())
                .nom(personne.getNom())
                .prenom(personne.getPrenom())
                .dateNaissance(personne.getDateNaissance())
                .lieuNaissance(personne.getLieuNaissance())
                .genre(personne.getGenre().toString())
                .dateDeces(personne.getDateDeces())
                .photo(personne.getPhoto())
                .empreinte(personne.getEmpreinte())
                .adresses(personne.getAdresses().stream().map(AdresseResponse::fromEntity).toList())
                .telephones(personne.getTelephones().stream().map(TelephoneResponse::fromEntity).toList())
                .build();
    }

}
