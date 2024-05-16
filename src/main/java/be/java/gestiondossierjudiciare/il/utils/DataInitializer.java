package be.java.gestiondossierjudiciare.il.utils;


import be.java.gestiondossierjudiciare.dal.repositories.*;
import be.java.gestiondossierjudiciare.domain.entities.*;
import be.java.gestiondossierjudiciare.domain.enums.Genre;
import be.java.gestiondossierjudiciare.domain.enums.Role;
import be.java.gestiondossierjudiciare.domain.enums.Statut;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PersonneRepository personneRepository;
    private final AdresseRepository adresseRepository;
    private final TelephoneRepository telephoneRepository;
    private final PlainteRepository plainteRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (personneRepository.count()==0){
            Personne personne1 = new Personne("881113-237-37","Hassaini", "Azzedine", LocalDate.of(1988,11,13).atStartOfDay(), "La Louvière", Genre.HOMME, null, "", "");
            Personne personne2 = new Personne("112233-123-34","Collignon", "Valentine", LocalDate.of(1999,3,23).atStartOfDay(), "Dinant", Genre.FEMME, null, "", "");
            Personne personne3 = new Personne("881113-237-36","Georis", "Antoine", LocalDate.of(1999,9,9).atStartOfDay(), "Charleroi", Genre.HOMME, null, "", "");
            Personne personne4 = new Personne("810208-183-31","Quinet", "Nicolas", LocalDate.of(1981,2,8).atStartOfDay(), "Ottignies", Genre.HOMME, null, "", "");
            personneRepository.save(personne1);
            personneRepository.save(personne2);
            personneRepository.save(personne3);
            personneRepository.save(personne4);

            Utilisateur admin = Utilisateur.builder()
                            .email("antoinegeoris@outlook.be")
                            .motDePasse(passwordEncoder.encode("09091999"))
                            .role(Role.ADMIN)
                            .personne(personne3)
                            .build();

            Utilisateur agent = Utilisateur.builder()
                                        .email("quinet.nicolas@gmail.com")
                                        .motDePasse(passwordEncoder.encode("12341234"))
                                        .role(Role.AGENT)
                                        .personne(personne4)
                                        .build();

            Utilisateur avocat = Utilisateur.builder()
                                        .email("valentine@gmail.com")
                                        .motDePasse(passwordEncoder.encode("43214321"))
                                        .role(Role.AVOCAT)
                                        .personne(personne2)
                                        .build();

            utilisateurRepository.save(admin);
            utilisateurRepository.save(agent);
            utilisateurRepository.save(avocat);

            Adresse adresse1 = new Adresse("Rue test", "1", "Ville", "1234", "Belgique", "Domicile", personne1);
            Adresse adresse2 = new Adresse("Rue test2", "1", "Ville", "1234", "Belgique", "Domicile", personne2);
            Adresse adresse3 = new Adresse("Rue test3", "1", "Ville", "1234", "Belgique", "Domicile", personne3);
            adresseRepository.save(adresse1);
            adresseRepository.save(adresse2);
            adresseRepository.save(adresse3);

            Telephone tel1 = new Telephone("0498123456", "GSM", personne1);
            Telephone tel2 = new Telephone("0498123457", "GSM", personne2);
            Telephone tel3 = new Telephone("0498123458", "GSM", personne3);
            telephoneRepository.save(tel1);
            telephoneRepository.save(tel2);
            telephoneRepository.save(tel3);

            Plainte plainte = new Plainte("1234-5678", Statut.ENREGISTREE, LocalDateTime.now(), personne2, personne1);
            plainte.getPersonnesConcernes().add(personne3);

            plainteRepository.save(plainte);

        }
    }
}

