package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Dépôt pour les opérations de persistance des entités Personne.
 *
 * Fournit des méthodes pour les opérations CRUD de base.
 * Hérite de JpaRepository pour les opérations CRUD.
 */
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {

}
