package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface pour les opérations de persistance des entités Personne.
 * Fournit des méthodes pour les opérations CRUD de base.
 * Hérite de JpaRepository pour les opérations CRUD.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByNationalRegister(String nationalRegister);

    @Query("SELECT p FROM Person p WHERE p.lawyer.id = :lawyerId")
    List<Person> findCustomersByLawyer(Long lawyerId);

}
