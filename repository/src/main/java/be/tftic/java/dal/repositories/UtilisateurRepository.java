package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    @Query("select c from Utilisateur c where c.email like :email")
    Optional<Utilisateur> getUserByUsername(String email);
}
