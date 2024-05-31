package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.UserService;
import be.tftic.java.common.models.requests.auth.LoginRequest;
import be.tftic.java.common.models.requests.auth.RegisterRequest;
import be.tftic.java.common.models.responses.UserTokenResponse;
import be.tftic.java.dal.repositories.PersonRepository;
import be.tftic.java.dal.repositories.UserRepository;
import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.entities.User;
import be.tftic.java.domain.enums.Role;
import be.tftic.java.il.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Classe de service pour la gestion des opérations liées à l'entité Utilisateur.
 * Cette classe fournit une couche d'abstraction entre la couche de contrôleur et la couche de persistance,
 * permettant de gérer les opérations métier et de maintenir une séparation des préoccupations.
 *
 * @Service indique que cette classe est un composant Spring géré par le conteneur d'injection de dépendances.
 * Spring s'occupe de créer une instance unique de cette classe et de la fournir là où elle est nécessaire.
 * @RequiredArgsConstructor indique que le constructeur généré par Lombok ne prend en compte que les attributs finaux.
 * Dans ce cas, cela signifie que le constructeur injecte les instances de UtilisateurRepository et PasswordEncoder fournies par Spring.
 * UtilisateurService indique que cette classe implémente l'interface UtilisateurService,
 * ce qui permet de garantir que les méthodes nécessaires sont fournies et facilite le remplacement ou l'extension de l'implémentation.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PersonServiceImpl personService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    /**
     * Récupère un utilisateur à partir de son nom d'utilisateur.
     * Si l'utilisateur n'existe pas, une exception UsernameNotFoundException est levée.
     *
     * @param username le nom d'utilisateur de l'utilisateur à récupérer.
     * @return l'utilisateur correspondant au nom d'utilisateur donné.
     * @throws UsernameNotFoundException si l'utilisateur n'existe pas.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username).orElseThrow();
    }

    /**
     * Connecte un utilisateur en vérifiant son nom d'utilisateur (adresse e-mail) et son mot de passe.
     * Si l'utilisateur n'existe pas ou si le mot de passe est incorrect, une exception RuntimeException est levée.
     *
     * @param request les informations de connexion de l'utilisateur, y compris le nom d'utilisateur (adresse e-mail) et le mot de passe.
     * @return l'utilisateur connecté.
     * @throws RuntimeException si l'utilisateur n'existe pas ou si le mot de passe est incorrect.
     */
    @Override
    public UserTokenResponse login(LoginRequest request) {
        User existingUser = userRepository.getUserByUsername(request.mail()).orElseThrow();
        UserTokenResponse dto = UserTokenResponse.fromEntity(existingUser);
        String token = jwtUtils.generateToken(existingUser);
        dto.setToken(token);

        if (!passwordEncoder.matches(request.password(), existingUser.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return dto;
    }

    /**
     * Register a new User as a Citizen or a Lawyer
     * @param request User dto containing all information needed to register
     * @param role Role of the user (Citizen or Lawyer)
     * @return USerTokenDTO : DTO containing the needed information to authenticate a user (mail + JWT Token)
     */
    @Override
    public UserTokenResponse register(RegisterRequest request, Role role) {

        if (userRepository.existsByEmail(request.mail())) {
            throw new RuntimeException("User with email " + request.mail() + " already exist.");
        }
        String hashedPassword = passwordEncoder.encode(request.password());

        Person p;
        // Check if the person entity already exists
        if (personService.existsByNationalRegister(request.person().nationalRegisterNumber())){
            p = personService.findByNationalRegister(request.person().nationalRegisterNumber());
        } else {
            p = personService.create(request.person());
        }

        User user = User.builder()
                .mail(request.mail())
                .password(hashedPassword)
                .role(role)
                .person(p)
                .build();

        userRepository.save(user);

        UserTokenResponse dto = UserTokenResponse.fromEntity(user);
        String token = jwtUtils.generateToken(user);
        dto.setToken(token);

        return dto;
    }

}
