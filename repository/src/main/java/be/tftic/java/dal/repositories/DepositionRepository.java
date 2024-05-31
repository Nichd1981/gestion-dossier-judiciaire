package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Deposition;
import be.tftic.java.domain.entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Interface pour les opérations de persistance des entités Deposition.
 * Fournit des méthodes pour les opérations CRUD de base ainsi que des capacités de requête spécifiée.
 * Hérite de JpaRepository pour les opérations CRUD et de JpaSpecificationExecutor
 * pour les requêtes basées sur des spécifications.
 */
@Repository
public interface DepositionRepository extends JpaRepository<Deposition, Long>, JpaSpecificationExecutor<Deposition> {

    /**
     * Récupère une liste de dépositions associées à une plainte spécifique.
     *
     * @param complaint l'entité Plainte pour laquelle récupérer les dépositions
     * @return une liste d'entités Deposition associées à la plainte spécifiée
     */
    @Query("SELECT d FROM Deposition d WHERE d.complaint = :complaint")
    List<Deposition> findByComplaint(Complaint complaint);

}
