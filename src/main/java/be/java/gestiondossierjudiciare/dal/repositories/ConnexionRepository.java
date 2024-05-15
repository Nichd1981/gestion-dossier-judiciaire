package be.java.gestiondossierjudiciare.dal.repositories;

import be.java.gestiondossierjudiciare.domain.entities.Connexion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConnexionRepository extends JpaRepository<Connexion, Long> {

    @Query("select c from Connexion c where c.email like :email")
    Optional<Connexion> getUserByUsername(String email);
}
