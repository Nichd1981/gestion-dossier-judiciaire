package be.tftic.java.il.utils;

import be.tftic.java.dal.repositories.*;
import be.tftic.java.domain.entities.*;
import be.tftic.java.domain.enums.Gender;
import be.tftic.java.domain.enums.JudgmentDecision;
import be.tftic.java.domain.enums.Role;
import be.tftic.java.domain.enums.ComplaintStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private final JudgmentRepository judgmentRepository;

    /**
     * Méthode exécutée au démarrage de l'application pour initialiser les données.
     * @param args les arguments de la ligne de commande, s'ils existent
     */

    @Override
    public void run(String... args) {
        if (personRepository.count()==0){

            // Personnes
            List<Person> persons = new ArrayList<>();
            persons.add(new Person("900101-123-01", "Dupont", "Jean", LocalDate.of(1990, 1, 1), "Liège", Gender.MALE, null, "", ""));
            persons.add(new Person("920202-234-02", "Martin", "Sophie", LocalDate.of(1992, 2, 2), "Namur", Gender.FEMALE, null, "", ""));
            persons.add(new Person("880303-345-03", "Leroy", "Luc", LocalDate.of(1988, 3, 3), "Bruxelles", Gender.MALE, null, "", ""));
            persons.add(new Person("950404-456-04", "Dubois", "Marie", LocalDate.of(1995, 4, 4), "Anvers", Gender.FEMALE, null, "", ""));
            persons.add(new Person("910505-567-05", "Lambert", "Pierre", LocalDate.of(1991, 5, 5), "Gand", Gender.MALE, null, "", ""));
            persons.add(new Person("930606-678-06", "Rousseau", "Claire", LocalDate.of(1993, 6, 6), "Mons", Gender.FEMALE, null, "", ""));
            persons.add(new Person("870707-789-07", "Lefebvre", "Michel", LocalDate.of(1987, 7, 7), "Charleroi", Gender.MALE, null, "", ""));
            persons.add(new Person("940808-890-08", "Petit", "Anne", LocalDate.of(1994, 8, 8), "Bruges", Gender.FEMALE, null, "", ""));
            persons.add(new Person("890909-901-09", "Moreau", "Thomas", LocalDate.of(1989, 9, 9), "Louvain", Gender.MALE, null, "", ""));
            persons.add(new Person("960101-012-10", "Legrand", "Isabelle", LocalDate.of(1996, 1, 1), "Verviers", Gender.FEMALE, null, "", ""));
            personRepository.saveAll(persons);

            // Adresses et numéros de téléphone
            List<Address> addresses = new ArrayList<>();
            addresses.add(new Address("Rue de la Cathédrale", "15", "Liège", "4000", "Belgique", "Domicile", persons.get(0)));
            addresses.add(new Address("Avenue de la Citadelle", "8", "Namur", "5000", "Belgique", "Domicile", persons.get(1)));
            addresses.add(new Address("Rue Royale", "120", "Bruxelles", "1000", "Belgique", "Domicile", persons.get(2)));
            addresses.add(new Address("Meir", "25", "Anvers", "2000", "Belgique", "Domicile", persons.get(3)));
            addresses.add(new Address("Veldstraat", "10", "Gand", "9000", "Belgique", "Domicile", persons.get(4)));
            addresses.add(new Address("Grand-Place", "5", "Mons", "7000", "Belgique", "Domicile", persons.get(5)));
            addresses.add(new Address("Boulevard Tirou", "45", "Charleroi", "6000", "Belgique", "Domicile", persons.get(6)));
            addresses.add(new Address("Markt", "12", "Bruges", "8000", "Belgique", "Domicile", persons.get(7)));
            addresses.add(new Address("Bondgenotenlaan", "30", "Louvain", "3000", "Belgique", "Domicile", persons.get(8)));
            addresses.add(new Address("Rue du Centre", "18", "Verviers", "4800", "Belgique", "Domicile", persons.get(9)));
            addressRepository.saveAll(addresses);
            List<Phone> phones = new ArrayList<>();
            phones.add(new Phone("0494012345", "GSM", persons.get(0)));
            phones.add(new Phone("0495123456", "GSM", persons.get(1)));
            phones.add(new Phone("0476234567", "GSM", persons.get(2)));
            phones.add(new Phone("0487345678", "GSM", persons.get(3)));
            phones.add(new Phone("0498456789", "GSM", persons.get(4)));
            phones.add(new Phone("0479567890", "GSM", persons.get(5)));
            phones.add(new Phone("0490678901", "GSM", persons.get(6)));
            phones.add(new Phone("0491789012", "GSM", persons.get(7)));
            phones.add(new Phone("0492890123", "GSM", persons.get(8)));
            phones.add(new Phone("0493901234", "GSM", persons.get(9)));
            phoneRepository.saveAll(phones);

            // Utilisateurs (avec mot de passe "12341234")
            List<User> users = new ArrayList<>();
            users.add(User.builder().mail("jean.dupont@email.com").password(passwordEncoder.encode("12341234")).role(Role.ADMIN).person(persons.get(0)).build());
            users.add(User.builder().mail("sophie.martin@email.com").password(passwordEncoder.encode("12341234")).role(Role.AGENT).person(persons.get(1)).build());
            users.add(User.builder().mail("luc.leroy@email.com").password(passwordEncoder.encode("12341234")).role(Role.CITIZEN).person(persons.get(2)).build());
            users.add(User.builder().mail("marie.dubois@email.com").password(passwordEncoder.encode("12341234")).role(Role.LAWYER).person(persons.get(3)).build());
            users.add(User.builder().mail("pierre.lambert@email.com").password(passwordEncoder.encode("12341234")).role(Role.CITIZEN).person(persons.get(4)).build());
            users.add(User.builder().mail("claire.rousseau@email.com").password(passwordEncoder.encode("12341234")).role(Role.AGENT).person(persons.get(5)).build());
            users.add(User.builder().mail("michel.lefebvre@email.com").password(passwordEncoder.encode("12341234")).role(Role.CITIZEN).person(persons.get(6)).build());
            users.add(User.builder().mail("anne.petit@email.com").password(passwordEncoder.encode("12341234")).role(Role.LAWYER).person(persons.get(7)).build());
            users.add(User.builder().mail("thomas.moreau@email.com").password(passwordEncoder.encode("12341234")).role(Role.CITIZEN).person(persons.get(8)).build());
            users.add(User.builder().mail("isabelle.legrand@email.com").password(passwordEncoder.encode("12341234")).role(Role.AGENT).person(persons.get(9)).build());
            userRepository.saveAll(users);

            // Plaintes (20)
            List<Complaint> complaints = new ArrayList<>();
            complaints.add(new Complaint("DUP-2024-0001", ComplaintStatus.REGISTERED, LocalDate.of(2024, 1, 15).atStartOfDay(), persons.get(0), persons.get(1)));
            complaints.add(new Complaint("MAR-2024-0002", ComplaintStatus.IN_PROGRESS, LocalDate.of(2024, 2, 20).atStartOfDay(), persons.get(1), persons.get(2)));
            complaints.add(new Complaint("LER-2024-0003", ComplaintStatus.CLOSED, LocalDate.of(2024, 3, 10).atStartOfDay(), persons.get(2), persons.get(3)));
            complaints.add(new Complaint("DUB-2024-0004", ComplaintStatus.REGISTERED, LocalDate.of(2024, 4, 5).atStartOfDay(), persons.get(3), persons.get(4)));
            complaints.add(new Complaint("LAM-2024-0005", ComplaintStatus.IN_PROGRESS, LocalDate.of(2024, 5, 12).atStartOfDay(), persons.get(4), persons.get(5)));
            complaints.add(new Complaint("ROU-2024-0006", ComplaintStatus.CLOSED, LocalDate.of(2024, 6, 18).atStartOfDay(), persons.get(5), persons.get(6)));
            complaints.add(new Complaint("LEF-2024-0007", ComplaintStatus.REGISTERED, LocalDate.of(2024, 7, 22).atStartOfDay(), persons.get(6), persons.get(7)));
            complaints.add(new Complaint("PET-2024-0008", ComplaintStatus.IN_PROGRESS, LocalDate.of(2024, 8, 30).atStartOfDay(), persons.get(7), persons.get(8)));
            complaints.add(new Complaint("MOR-2024-0009", ComplaintStatus.CLOSED, LocalDate.of(2024, 9, 7).atStartOfDay(), persons.get(8), persons.get(9)));
            complaints.add(new Complaint("LEG-2024-0010", ComplaintStatus.REGISTERED, LocalDate.of(2024, 10, 14).atStartOfDay(), persons.get(9), persons.get(0)));
            complaints.add(new Complaint("DUP-2024-0011", ComplaintStatus.IN_PROGRESS, LocalDate.of(2024, 11, 19).atStartOfDay(), persons.get(0), persons.get(2)));
            complaints.add(new Complaint("MAR-2024-0012", ComplaintStatus.CLOSED, LocalDate.of(2024, 12, 25).atStartOfDay(), persons.get(1), persons.get(3)));
            complaints.add(new Complaint("LER-2025-0001", ComplaintStatus.REGISTERED, LocalDate.of(2025, 1, 2).atStartOfDay(), persons.get(2), persons.get(4)));
            complaints.add(new Complaint("DUB-2025-0002", ComplaintStatus.IN_PROGRESS, LocalDate.of(2025, 2, 8).atStartOfDay(), persons.get(3), persons.get(5)));
            complaints.add(new Complaint("LAM-2025-0003", ComplaintStatus.CLOSED, LocalDate.of(2025, 3, 15).atStartOfDay(), persons.get(4), persons.get(6)));
            complaints.add(new Complaint("ROU-2025-0004", ComplaintStatus.REGISTERED, LocalDate.of(2025, 4, 21).atStartOfDay(), persons.get(5), persons.get(7)));
            complaints.add(new Complaint("LEF-2025-0005", ComplaintStatus.IN_PROGRESS, LocalDate.of(2025, 5, 27).atStartOfDay(), persons.get(6), persons.get(8)));
            complaints.add(new Complaint("PET-2025-0006", ComplaintStatus.CLOSED, LocalDate.of(2025, 6, 3).atStartOfDay(), persons.get(7), persons.get(9)));
            complaints.add(new Complaint("MOR-2025-0007", ComplaintStatus.REGISTERED, LocalDate.of(2025, 7, 9).atStartOfDay(), persons.get(8), persons.get(0)));
            complaints.add(new Complaint("LEG-2025-0008", ComplaintStatus.IN_PROGRESS, LocalDate.of(2025, 8, 16).atStartOfDay(), persons.get(9), persons.get(1)));
            complaintRepository.saveAll(complaints);

            // Auditions (20)
            List<Audition> auditions = new ArrayList<>();
            auditions.add(new Audition(LocalDateTime.of(2024, 2, 1, 10, 0), "1", "Audition pour plainte DUP-2024-0001", persons.get(1), persons.get(2), persons.get(4), complaints.get(1)));
            auditions.add(new Audition(LocalDateTime.of(2024, 3, 5, 14, 30), "2", "Audition pour plainte MAR-2024-0002", persons.get(2), persons.get(3), persons.get(8), complaints.get(2)));
            auditions.add(new Audition(LocalDateTime.of(2024, 4, 10, 9, 15), "3", "Audition pour plainte LER-2024-0003", persons.get(3), persons.get(4), persons.get(8), complaints.get(3)));
            auditions.add(new Audition(LocalDateTime.of(2024, 5, 15, 11, 45), "4", "Audition pour plainte DUB-2024-0004", persons.get(4), persons.get(5), persons.get(4), complaints.get(4)));
            auditions.add(new Audition(LocalDateTime.of(2024, 6, 20, 13, 30), "5", "Audition pour plainte LAM-2024-0005", persons.get(5), persons.get(6), persons.get(8), complaints.get(5)));
            auditions.add(new Audition(LocalDateTime.of(2024, 7, 25, 15, 0), "6", "Audition pour plainte ROU-2024-0006", persons.get(6), persons.get(7), persons.get(4), complaints.get(6)));
            auditions.add(new Audition(LocalDateTime.of(2024, 8, 30, 10, 30), "7", "Audition pour plainte LEF-2024-0007", persons.get(7), persons.get(8), persons.get(8), complaints.get(7)));
            auditions.add(new Audition(LocalDateTime.of(2024, 10, 5, 14, 0), "8", "Audition pour plainte PET-2024-0008", persons.get(8), persons.get(9), persons.get(4), complaints.get(8)));
            auditions.add(new Audition(LocalDateTime.of(2024, 11, 10, 9, 45), "9", "Audition pour plainte MOR-2024-0009", persons.get(9), persons.get(10), persons.get(8), complaints.get(9)));
            auditions.add(new Audition(LocalDateTime.of(2024, 12, 15, 11, 15), "10", "Audition pour plainte LEG-2024-0010", persons.get(10), persons.get(1), persons.get(4), complaints.get(10)));
            auditions.add(new Audition(LocalDateTime.of(2025, 1, 20, 13, 0), "11", "Audition pour plainte DUP-2024-0011", persons.get(1), persons.get(3), persons.get(8), complaints.get(11)));
            auditions.add(new Audition(LocalDateTime.of(2025, 2, 25, 15, 30), "12", "Audition pour plainte MAR-2024-0012", persons.get(2), persons.get(4), persons.get(4), complaints.get(12)));
            auditions.add(new Audition(LocalDateTime.of(2025, 3, 2, 10, 0), "13", "Audition pour plainte LER-2025-0001", persons.get(3), persons.get(5), persons.get(8), complaints.get(13)));
            auditions.add(new Audition(LocalDateTime.of(2025, 4, 7, 14, 30), "14", "Audition pour plainte DUB-2025-0002", persons.get(4), persons.get(6), persons.get(4), complaints.get(14)));
            auditions.add(new Audition(LocalDateTime.of(2025, 5, 12, 9, 15), "15", "Audition pour plainte LAM-2025-0003", persons.get(5), persons.get(7), persons.get(8), complaints.get(15)));
            auditions.add(new Audition(LocalDateTime.of(2025, 6, 17, 11, 45), "16", "Audition pour plainte ROU-2025-0004", persons.get(6), persons.get(8), persons.get(4), complaints.get(16)));
            auditions.add(new Audition(LocalDateTime.of(2025, 7, 22, 13, 30), "17", "Audition pour plainte LEF-2025-0005", persons.get(7), persons.get(9), persons.get(8), complaints.get(17)));
            auditions.add(new Audition(LocalDateTime.of(2025, 8, 27, 15, 0), "18", "Audition pour plainte PET-2025-0006", persons.get(8), persons.get(10), persons.get(4), complaints.get(18)));
            auditions.add(new Audition(LocalDateTime.of(2025, 10, 2, 10, 30), "19", "Audition pour plainte MOR-2025-0007", persons.get(9), persons.get(1), persons.get(8), complaints.get(19)));
            auditions.add(new Audition(LocalDateTime.of(2025, 11, 7, 14, 0), "20", "Audition pour plainte LEG-2025-0008", persons.get(10), persons.get(2), persons.get(4), complaints.get(20)));
            auditionRepository.saveAll(auditions);

            // Jugements (20)
            List<Judgment> judgments = new ArrayList<>();
            judgments.add(new Judgment(LocalDateTime.of(2024, 6, 15, 9, 0), JudgmentDecision.CONDEMNATION, "Jugement pour DUP-2024-0001", complaints.get(0)));
            judgments.add(new Judgment(LocalDateTime.of(2024, 7, 20, 11, 0), JudgmentDecision.DISMISSED, "Jugement pour MAR-2024-0002", complaints.get(1)));
            judgments.add(new Judgment(LocalDateTime.of(2024, 8, 25, 14, 0), JudgmentDecision.CONDEMNATION, "Jugement pour LER-2024-0003", complaints.get(2)));
            judgments.add(new Judgment(LocalDateTime.of(2024, 9, 30, 10, 30), JudgmentDecision.DISMISSED, "Jugement pour DUB-2024-0004", complaints.get(3)));
            judgments.add(new Judgment(LocalDateTime.of(2024, 11, 5, 13, 30), JudgmentDecision.CONDEMNATION, "Jugement pour LAM-2024-0005", complaints.get(4)));
            judgments.add(new Judgment(LocalDateTime.of(2024, 12, 10, 15, 0), JudgmentDecision.DISMISSED, "Jugement pour ROU-2024-0006", complaints.get(5)));
            judgments.add(new Judgment(LocalDateTime.of(2025, 1, 15, 9, 30), JudgmentDecision.CONDEMNATION, "Jugement pour LEF-2024-0007", complaints.get(6)));
            judgments.add(new Judgment(LocalDateTime.of(2025, 2, 20, 11, 30), JudgmentDecision.DISMISSED, "Jugement pour PET-2024-0008", complaints.get(7)));
            judgments.add(new Judgment(LocalDateTime.of(2025, 3, 25, 14, 30), JudgmentDecision.CONDEMNATION, "Jugement pour MOR-2024-0009", complaints.get(8)));
            judgments.add(new Judgment(LocalDateTime.of(2025, 4, 30, 10, 0), JudgmentDecision.DISMISSED, "Jugement pour LEG-2024-0010", complaints.get(9)));
            judgments.add(new Judgment(LocalDateTime.of(2025, 6, 5, 13, 0), JudgmentDecision.CONDEMNATION, "Jugement pour DUP-2024-0011", complaints.get(10)));
            judgments.add(new Judgment(LocalDateTime.of(2025, 7, 10, 15, 30), JudgmentDecision.DISMISSED, "Jugement pour MAR-2024-0012", complaints.get(11)));
            judgments.add(new Judgment(LocalDateTime.of(2025, 8, 15, 9, 0), JudgmentDecision.CONDEMNATION, "Jugement pour LER-2025-0001", complaints.get(12)));
            judgments.add(new Judgment(LocalDateTime.of(2025, 9, 20, 11, 0), JudgmentDecision.DISMISSED, "Jugement pour DUB-2025-0002", complaints.get(13)));
            judgments.add(new Judgment(LocalDateTime.of(2025, 10, 25, 14, 0), JudgmentDecision.CONDEMNATION, "Jugement pour LAM-2025-0003", complaints.get(14)));
            judgments.add(new Judgment(LocalDateTime.of(2025, 11, 30, 10, 30), JudgmentDecision.DISMISSED, "Jugement pour ROU-2025-0004", complaints.get(15)));
            judgments.add(new Judgment(LocalDateTime.of(2026, 1, 5, 13, 30), JudgmentDecision.CONDEMNATION, "Jugement pour LEF-2025-0005", complaints.get(16)));
            judgments.add(new Judgment(LocalDateTime.of(2026, 2, 10, 15, 0), JudgmentDecision.DISMISSED, "Jugement pour PET-2025-0006", complaints.get(17)));
            judgments.add(new Judgment(LocalDateTime.of(2026, 3, 15, 9, 30), JudgmentDecision.CONDEMNATION, "Jugement pour MOR-2025-0007", complaints.get(18)));
            judgments.add(new Judgment(LocalDateTime.of(2026, 4, 20, 11, 30), JudgmentDecision.DISMISSED, "Jugement pour LEG-2025-0008", complaints.get(19)));
            judgmentRepository.saveAll(judgments);

        }
    }
}

