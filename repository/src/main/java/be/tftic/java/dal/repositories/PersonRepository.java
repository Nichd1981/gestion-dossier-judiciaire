package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Person;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByNationalRegister(String nationalRegister);

}
