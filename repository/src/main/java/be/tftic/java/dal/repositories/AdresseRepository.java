package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Dépôt pour les opérations de persistance des entités Adresse.
 *
 * Fournit des méthodes pour les opérations CRUD de base.
 * Hérite de JpaRepository pour les opérations CRUD.
 */
@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
}
