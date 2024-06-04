package be.tftic.java.il.utils;


import be.tftic.java.dal.repositories.*;
import be.tftic.java.domain.entities.*;
import be.tftic.java.domain.enums.Gender;
import be.tftic.java.domain.enums.Role;
import be.tftic.java.domain.enums.ComplaintStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe responsable de l'initialisation des données au démarrage de l'application.
 * Implémente l'interface {@link CommandLineRunner} pour exécuter des opérations
 * lors du démarrage de l'application en tant que tâche de fond.
 * Cette classe est annotée avec {@link Component} et {@link RequiredArgsConstructor}
 * pour être détectée automatiquement comme un composant Spring et pour injecter
 * automatiquement les dépendances via le constructeur.
 * La méthode {@link #run(String...)} est exécutée au démarrage de l'application
 * et vérifie si des données initiales doivent être créées dans les repositories.
 * Si aucun enregistrement n'existe dans le repository des personnes, des exemples
 * de données sont créés pour les entités Personne, Utilisateur, Adresse, Téléphone,
 * Plainte, Déposition et Audition à des fins de démonstration.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    //TODO : pdt le refactoring, passer par les service plutot que les repository

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;
    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;
    private final DepositionRepository depositionRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuditionRepository auditionRepository;

    /**
     * Méthode exécutée au démarrage de l'application pour initialiser les données.
     * @param args les arguments de la ligne de commande, s'ils existent
     */

    @Override
    public void run(String... args) {
        if (personRepository.count()==0){
            Person azzedine = new Person("881113-237-37","Hassaini", "Azzedine", LocalDate.of(1988,11,13).atStartOfDay(), "La Louvière", Gender.MALE, null, "", "");
            Person antoine = new Person("881113-237-36","Georis", "Antoine", LocalDate.of(1999,9,9).atStartOfDay(), "Charleroi", Gender.MALE, null, "", "");
            Person nicolas = new Person("810208-183-31","Quinet", "Nicolas", LocalDate.of(1981,2,8).atStartOfDay(), "Ottignies", Gender.MALE, null, "", "");
            Person valentine = new Person("112233-123-34","Collignon", "Valentine", LocalDate.of(1999,3,23).atStartOfDay(), "Dinant", Gender.FEMALE, null, "", "");
            valentine.setLawyer(azzedine);
            personRepository.save(azzedine);
            personRepository.save(valentine);
            personRepository.save(antoine);
            personRepository.save(nicolas);

            User admin = User.builder()
                            .mail("antoinegeoris@outlook.be")
                            .password(passwordEncoder.encode("12341234"))
                            .role(Role.ADMIN)
                            .person(antoine)
                            .build();

            User agent = User.builder()
                                .mail("quinet.nicolas@gmail.com")
                                .password(passwordEncoder.encode("12341234"))
                                .role(Role.AGENT)
                                .person(nicolas)
                                .build();

            User citizen = User.builder()
                                .mail("a.hassaini@stag.technofuturtic.education")
                                .password(passwordEncoder.encode("12341234"))
                                .role(Role.CITIZEN)
                                .person(valentine)
                                .build();

            User citizen2 = User.builder()
                    .mail("antoinegeoris99@outlook.be")
                    .password(passwordEncoder.encode("12341234"))
                    .role(Role.CITIZEN)
                    .person(antoine)
                    .build();

            User lawyer = User.builder()
                    .mail("azzedinehassaini@gmail.com")
                    .password(passwordEncoder.encode("12341234"))
                    .role(Role.LAWYER)
                    .person(azzedine)
                    .build();

            userRepository.save(admin);
            userRepository.save(agent);
            userRepository.save(citizen);
            userRepository.save(citizen2);
            userRepository.save(lawyer);

            Address address1 = new Address("Rue test", "1", "Ville", "1234", "Belgique", "Domicile", azzedine);
            Address address2 = new Address("Rue test2", "1", "Ville", "1234", "Belgique", "Domicile", valentine);
            Address address3 = new Address("Rue test3", "1", "Ville", "1234", "Belgique", "Domicile", antoine);
            Address address4 = new Address("Rue test4", "1", "Ville", "1234", "Belgique", "Domicile", nicolas);
            addressRepository.save(address1);
            addressRepository.save(address2);
            addressRepository.save(address3);
            addressRepository.save(address4);

            Phone phone1 = new Phone("0498123456", "GSM", azzedine);
            Phone phone2 = new Phone("0498123457", "GSM", valentine);
            Phone phone3 = new Phone("0498123458", "GSM", antoine);
            Phone phone4 = new Phone("0498123459", "GSM", nicolas);
            phoneRepository.save(phone1);
            phoneRepository.save(phone2);
            phoneRepository.save(phone3);
            phoneRepository.save(phone4);

            Complaint complaint = new Complaint("VAL-1234-5678", ComplaintStatus.REGISTERED, LocalDate.of(2024,1,10).atStartOfDay(), valentine, nicolas);
            complaint.getPersonConcerned().add(antoine);
            complaintRepository.save(complaint);

            Complaint complaint2 = new Complaint("AZZ-1234-5679", ComplaintStatus.REGISTERED, LocalDate.of(2024,2,10).atStartOfDay(), azzedine, nicolas);
            complaint2.getPersonConcerned().add(antoine);
            complaintRepository.save(complaint2);

            Complaint complaint3 = new Complaint("VAL-1234-5670", ComplaintStatus.IN_PROGRESS, LocalDate.of(2024,3,10).atStartOfDay(), valentine, nicolas);
            complaint3.getPersonConcerned().add(antoine);
            complaintRepository.save(complaint3);

            Complaint complaint4 = new Complaint("ANT-1234-5670", ComplaintStatus.IN_PROGRESS, LocalDate.of(2024,4,10).atStartOfDay(), antoine, nicolas);
            complaint4.getPersonConcerned().add(valentine);
            complaintRepository.save(complaint4);

            Deposition deposition = new Deposition(LocalDate.of(2024, 1, 5), "Ceci est une arnaque !", complaint);
            depositionRepository.save(deposition);
            Deposition deposition2 = new Deposition(LocalDate.of(2024, 10, 5), "C'est quoi ça encore ?!", complaint);
            depositionRepository.save(deposition2);
            Deposition deposition3 = new Deposition(LocalDate.of(2024, 5, 5), "Je suis perdu !", complaint);
            depositionRepository.save(deposition3);

            Audition audition  = new Audition(LocalDateTime.of(2024, 1, 5, 10, 0), "2", "Ceci est une audition !", valentine, nicolas, azzedine, complaint );
            auditionRepository.save(audition);
            Audition audition2  = new Audition(LocalDateTime.of(2025, 7, 7, 10, 0), "2", "Ceci est une audition Bis repetita !", valentine, nicolas, azzedine, complaint );
            auditionRepository.save(audition2);

        }
    }
}

