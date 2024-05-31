package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Audition;
import be.tftic.java.domain.entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * Interface des opérations de persistance des entités Audition.
 * Fournit des méthodes pour les opérations CRUD de base ainsi que des capacités de requête spécifiée.
 * Hérite de JpaRepository pour les opérations CRUD et de JpaSpecificationExecutor
 * Pour les requêtes basées sur des spécifications.
 */
public interface AuditionRepository extends JpaRepository<Audition, Long>, JpaSpecificationExecutor<Audition> {

	@Query("SELECT a FROM Audition a WHERE a.complaint = :complaint")
	List<Audition> findByComplaint(Complaint complaint);

}
