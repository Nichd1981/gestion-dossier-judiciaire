package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Dépôt pour les opérations de persistance des entités Utilisateur.
 *
 * Fournit des méthodes pour les opérations CRUD de base ainsi que des capacités de requête spécifiée.
 * Hérite de JpaRepository pour les opérations CRUD.
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    /**
     * Récupère un utilisateur par son adresse e-mail.
     *
     * @param email l'adresse e-mail de l'utilisateur
     * @return un Optional contenant l'entité Utilisateur correspondante à l'adresse e-mail, ou vide si aucun utilisateur n'est trouvé
     */
    @Query("select c from Utilisateur c where c.email like :email")
    Optional<Utilisateur> getUserByUsername(String email);
}

