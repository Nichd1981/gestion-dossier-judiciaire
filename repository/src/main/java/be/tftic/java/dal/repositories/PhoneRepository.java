package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface pour les opérations de persistance des entités Telephone.
 * Fournit des méthodes pour les opérations CRUD de base.
 * Hérite de JpaRepository pour les opérations CRUD.
 */
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
