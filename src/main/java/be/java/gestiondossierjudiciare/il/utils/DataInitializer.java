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
    public void run(String... args) {
        if (personneRepository.count()==0){
            Personne azzedine = new Personne("881113-237-37","Hassaini", "Azzedine", LocalDate.of(1988,11,13).atStartOfDay(), "La Louvière", Genre.HOMME, null, "", "");
            Personne antoine = new Personne("881113-237-36","Georis", "Antoine", LocalDate.of(1999,9,9).atStartOfDay(), "Charleroi", Genre.HOMME, null, "", "");
            Personne nicolas = new Personne("810208-183-31","Quinet", "Nicolas", LocalDate.of(1981,2,8).atStartOfDay(), "Ottignies", Genre.HOMME, null, "", "");
            Personne valentine = new Personne("112233-123-34","Collignon", "Valentine", LocalDate.of(1999,3,23).atStartOfDay(), "Dinant", Genre.FEMME, null, "", "");
            valentine.setAvocat(azzedine);
            personneRepository.save(azzedine);
            personneRepository.save(valentine);
            personneRepository.save(antoine);
            personneRepository.save(nicolas);

            Utilisateur admin = Utilisateur.builder()
                            .email("antoinegeoris@outlook.be")
                            .motDePasse(passwordEncoder.encode("12341234"))
                            .role(Role.ADMIN)
                            .personne(antoine)
                            .build();

            Utilisateur agent = Utilisateur.builder()
                                        .email("quinet.nicolas@gmail.com")
                                        .motDePasse(passwordEncoder.encode("12341234"))
                                        .role(Role.AGENT)
                                        .personne(nicolas)
                                        .build();

            Utilisateur citoyen = Utilisateur.builder()
                                        .email("valentineevidence83@gmail.com")
                                        .motDePasse(passwordEncoder.encode("12341234"))
                                        .role(Role.CITOYEN)
                                        .personne(valentine)
                                        .build();

            Utilisateur citoyen2 = Utilisateur.builder()
                    .email("antoinegeoris99@outlook.be")
                    .motDePasse(passwordEncoder.encode("12341234"))
                    .role(Role.CITOYEN)
                    .personne(antoine)
                    .build();

            Utilisateur avocat = Utilisateur.builder()
                    .email("azzedinehassaini@gmail.com")
                    .motDePasse(passwordEncoder.encode("12341234"))
                    .role(Role.AVOCAT)
                    .personne(azzedine)
                    .build();

            utilisateurRepository.save(admin);
            utilisateurRepository.save(agent);
            utilisateurRepository.save(citoyen);
            utilisateurRepository.save(citoyen2);
            utilisateurRepository.save(avocat);

            Adresse adresse1 = new Adresse("Rue test", "1", "Ville", "1234", "Belgique", "Domicile", azzedine);
            Adresse adresse2 = new Adresse("Rue test2", "1", "Ville", "1234", "Belgique", "Domicile", valentine);
            Adresse adresse3 = new Adresse("Rue test3", "1", "Ville", "1234", "Belgique", "Domicile", antoine);
            Adresse adresse4 = new Adresse("Rue test4", "1", "Ville", "1234", "Belgique", "Domicile", nicolas);
            adresseRepository.save(adresse1);
            adresseRepository.save(adresse2);
            adresseRepository.save(adresse3);
            adresseRepository.save(adresse4);

            Telephone tel1 = new Telephone("0498123456", "GSM", azzedine);
            Telephone tel2 = new Telephone("0498123457", "GSM", valentine);
            Telephone tel3 = new Telephone("0498123458", "GSM", antoine);
            Telephone tel4 = new Telephone("0498123459", "GSM", nicolas);
            telephoneRepository.save(tel1);
            telephoneRepository.save(tel2);
            telephoneRepository.save(tel3);
            telephoneRepository.save(tel4);

            Plainte plainte = new Plainte("1234-5678", Statut.ENREGISTREE, LocalDateTime.now(), valentine, nicolas);
            plainte.getPersonnesConcernees().add(antoine);

            plainteRepository.save(plainte);

        }
    }
}

