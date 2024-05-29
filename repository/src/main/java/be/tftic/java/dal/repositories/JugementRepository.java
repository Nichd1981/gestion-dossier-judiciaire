package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Jugement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
/**
 * Depôt pour les opérations de persistance des entités Jugement.
 *
 * Fournit des méthodes pour les opérations CRUD de base ainsi que des capacités de requête spécifiée.
 * Hérite de JpaRepository pour les opérations CRUD et de JpaSpecificationExecutor
 * pour les requêtes basées sur des spécifications.
 */
@Repository
public interface JugementRepository extends JpaRepository<Jugement, Long>, JpaSpecificationExecutor<Jugement> {
}
