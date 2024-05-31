package be.tftic.java.bll.services;

import be.tftic.java.common.models.requests.auth.LoginRequest;
import be.tftic.java.common.models.requests.auth.RegisterRequest;
import be.tftic.java.common.models.responses.UserTokenResponse;
import be.tftic.java.domain.entities.User;
import be.tftic.java.domain.enums.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Interface pour les opérations de gestion des utilisateurs.
 * Étend l'interface UserDetailsService pour la gestion de l'authentification des utilisateurs,
 * et fournit une méthode pour la connexion d'un utilisateur.
 */
public interface UserService extends UserDetailsService {

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
     * @param request l'objet Utilisateur contenant les informations d'identification de l'utilisateur
     * @return l'objet Utilisateur si les informations d'identification sont valides, sinon null
     */
    UserTokenResponse login(LoginRequest request);

    /**
     * Register a new User as a Citizen or a Lawyer
     * @param request User dto containing all information needed to register
     * @param role Role of the user (Citizen or Lawyer)
     * @return USerTokenDTO : DTO containing the needed information to authenticate a user (mail + JWT Token)
     */
    UserTokenResponse register(RegisterRequest request, Role role);


}
