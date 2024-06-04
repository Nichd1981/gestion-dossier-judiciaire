package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface pour les opérations de persistance des entités Telephone.
 * Fournit des méthodes pour les opérations CRUD de base.
 * Hérite de JpaRepository pour les opérations CRUD.
 */
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    @Query("""
            SELECT p
            FROM Phone p
            WHERE p.person = :person
            """)
    List<Phone> findByPerson(Person person);

}
