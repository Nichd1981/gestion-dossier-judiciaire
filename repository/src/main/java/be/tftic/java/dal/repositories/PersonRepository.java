package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface pour les opérations de persistance des entités Personne.
 * Fournit des méthodes pour les opérations CRUD de base.
 * Hérite de JpaRepository pour les opérations CRUD.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByNationalRegister(String nationalRegister);

    /**
     * Checks if a person already exists based on national register number
     * @param nationalRegister national register number to check
     * @return True if the person already exists in DB, false if the person doesn't exist in DB
     */
    @Query("""
            select count(p) > 0
            from Person p
            where p.nationalRegister = :nationalRegister
            """)
    boolean existsByNationalRegister(String nationalRegister);

}
