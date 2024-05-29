package be.tftic.java.bll.services;

import be.tftic.java.domain.entities.Utilisateur;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Service pour les opérations de gestion des utilisateurs.
 *
 * Étend l'interface UserDetailsService pour la gestion de l'authentification des utilisateurs,
 * et fournit une méthode pour la connexion d'un utilisateur.
 */
public interface UtilisateurService extends UserDetailsService {

    /**
     * Charge les détails d'un utilisateur par son nom d'utilisateur.
     *
     * @param username le nom d'utilisateur de l'utilisateur à charger
     * @return les détails de l'utilisateur chargé
     * @throws UsernameNotFoundException si aucun utilisateur avec le nom d'utilisateur spécifié n'est trouvé
     */
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Connecte un utilisateur en vérifiant ses informations d'identification.
     *
     * @param utilisateur l'objet Utilisateur contenant les informations d'identification de l'utilisateur
     * @return l'objet Utilisateur si les informations d'identification sont valides, sinon null
     */
    Utilisateur login(Utilisateur utilisateur);
}

