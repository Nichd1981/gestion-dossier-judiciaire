package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {

    Optional<Personne> findByRegistreNational(String registreNational);

}
