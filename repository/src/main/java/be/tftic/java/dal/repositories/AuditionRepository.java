package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Audition;
import be.tftic.java.domain.entities.Plainte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * Dépôt pour les opérations de persistance des entités Audition.
 *
 * Fournit des méthodes pour les opérations CRUD de base ainsi que des capacités de requête spécifiée.
 * Hérite de JpaRepository pour les opérations CRUD et de JpaSpecificationExecutor
 * Pour les requêtes basées sur des spécifications.
 */


public interface AuditionRepository extends JpaRepository<Audition, Long>, JpaSpecificationExecutor<Audition> {

	@Query("select d from Audition d where d.plainte = :plainte")
	List<Audition> findByPlainte(Plainte plainte);

}
