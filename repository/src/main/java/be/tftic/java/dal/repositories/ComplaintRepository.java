package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

/**
 * Interface pour les opérations de persistance des entités Plainte.
 * Fournit des méthodes pour les opérations CRUD de base ainsi que des capacités de requête spécifiée.
 * Hérite de JpaRepository pour les opérations CRUD et de JpaSpecificationExecutor
 * pour les requêtes basées sur des spécifications.
 */
@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long>, JpaSpecificationExecutor<Complaint> {

    /**
     * Récupère une liste de plaintes associées à l'identifiant d'un plaignant.
     * @param id l'identifiant du plaignant
     * @return une liste d'entités Plainte associées au plaignant spécifié
     */
    @Query("SELECT c FROM Complaint c where c.complainant.id = :id")
    List<Complaint> findByComplainantId(Long id);

    /**
     * Récupère une plainte par son numéro de dossier.
     *
     * @param fileNumber le numéro de dossier de la plainte
     * @return un Optional contenant l'entité Plainte correspondant au numéro de dossier, ou vide si aucune plainte n'est trouvée
     */
    @Query(" SELECT c FROM Complaint c WHERE c.fileNumber ILIKE :fileNumber")
    Optional<Complaint> findByFileNumber(String fileNumber);

    /**
     * Récupère une liste de plaintes associées à une personne concernée.
     *
     * @param person l'entité Personne concernée
     * @return une liste d'entités Plainte associées à la personne spécifiée
     */
    @Query("SELECT c FROM Complaint c WHERE :person MEMBER OF c.personConcerned")
    List<Complaint> findByPersonConcerned(Person person);

    @Query("SELECT c FROM Complaint c WHERE c.complainant.id = :customersId AND c.complainant.lawyer.id = :lawyerId")
    List<Complaint> findComplaintByCustomersAndLawyer(Long customersId, Long lawyerId);

}
