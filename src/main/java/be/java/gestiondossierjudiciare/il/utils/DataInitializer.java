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

    private final CitoyenRepository citoyenRepository;
    private final AdresseRepository adresseRepository;
    private final TelephoneRepository telephoneRepository;
    private final PlainteRepository plainteRepository;
    private final ConnexionRepository connexionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (citoyenRepository.count()==0){
            Citoyen citoyen1 = new Citoyen("881113-237-37","Hassaini", "Azzedine", LocalDate.of(1988,11,13).atStartOfDay(), "La Louvière", Genre.HOMME, null, "", "");
            Citoyen citoyen2 = new Citoyen("112233-123-34","Collignon", "Valentine", LocalDate.of(1999,3,23).atStartOfDay(), "Dinant", Genre.FEMME, null, "", "");
            Citoyen citoyen3 = new Citoyen("881113-237-36","Georis", "Antoine", LocalDate.of(1999,9,9).atStartOfDay(), "Charleroi", Genre.HOMME, null, "", "");
            Citoyen citoyen4 = new Citoyen("810208-183-31","Quinet", "Nicolas", LocalDate.of(1981,2,8).atStartOfDay(), "Ottignies", Genre.HOMME, null, "", "");
            citoyenRepository.save(citoyen1);
            citoyenRepository.save(citoyen2);
            citoyenRepository.save(citoyen3);
            citoyenRepository.save(citoyen4);

            Connexion admin = Connexion.builder()
                            .email("antoinegeoris@outlook.be")
                            .motDePasse(passwordEncoder.encode("09091999"))
                            .role(Role.ADMIN)
                            .citoyen(citoyen3)
                            .build();

            Connexion agent = Connexion.builder()
                                        .email("quinet.nicolas@gmail.com")
                                        .motDePasse(passwordEncoder.encode("12341234"))
                                        .role(Role.AGENT)
                                        .citoyen(citoyen4)
                                        .build();

            Connexion avocat = Connexion.builder()
                                        .email("valentine@gmail.com")
                                        .motDePasse(passwordEncoder.encode("43214321"))
                                        .role(Role.AVOCAT)
                                        .citoyen(citoyen2)
                                        .build();

            connexionRepository.save(admin);
            connexionRepository.save(agent);
            connexionRepository.save(avocat);

            Adresse adresse1 = new Adresse("Rue test", "1", "Ville", "1234", "Belgique", "Domicile", citoyen1);
            Adresse adresse2 = new Adresse("Rue test2", "1", "Ville", "1234", "Belgique", "Domicile", citoyen2);
            Adresse adresse3 = new Adresse("Rue test3", "1", "Ville", "1234", "Belgique", "Domicile", citoyen3);
            adresseRepository.save(adresse1);
            adresseRepository.save(adresse2);
            adresseRepository.save(adresse3);

            Telephone tel1 = new Telephone("0498123456", "GSM", citoyen1);
            Telephone tel2 = new Telephone("0498123457", "GSM", citoyen2);
            Telephone tel3 = new Telephone("0498123458", "GSM", citoyen3);
            telephoneRepository.save(tel1);
            telephoneRepository.save(tel2);
            telephoneRepository.save(tel3);

            Plainte plainte = new Plainte("1234-5678", Statut.ENREGISTREE, LocalDateTime.now(), citoyen2, citoyen1);
            plainte.getCitoyensConcernes().add(citoyen3);

            plainteRepository.save(plainte);

        }
    }
}

