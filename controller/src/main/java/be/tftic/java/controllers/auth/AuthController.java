package be.tftic.java.controllers.auth;

import be.tftic.java.bll.services.UserService;
import be.tftic.java.common.models.requests.auth.LoginRequest;
import be.tftic.java.common.models.requests.auth.RegisterRequest;
import be.tftic.java.common.models.responses.UserTokenResponse;
import be.tftic.java.domain.enums.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur REST pour la gestion de l'authentification des utilisateurs.
 *
 * @RestController indique que cette classe est un Contrôleur REST.
 * @RequiredArgsConstructor indique que le compilateur doit générer un constructeur avec les arguments requis.
 * @RequestMapping("/auth") indique que les requêtes HTTP qui correspondent à cette annotation seront traitées par cette classe.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    /**
     * Service pour la gestion des utilisateurs.
     */
    private final UserService userService;

    /**
     * Traite une requête HTTP POST pour l'authentification d'un utilisateur.
     *
     * @PreAuthorize("isAnonymous()") indique que cette méthode ne peut être appelée que si l'utilisateur n'est pas authentifié.
     * @PostMapping("/login") indique que cette méthode traite les requêtes HTTP POST envoyées à l'URL "/login".
     *
     * @param form les informations de connexion de l'utilisateur.
     * @return une réponse HTTP avec les informations de l'utilisateur et un jeton JWT.
     */
    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<UserTokenResponse> login(@Valid @RequestBody LoginRequest form) {
        return ResponseEntity.ok(userService.login(form));
    }

    /**
     * Allows a citizen to register
     * @param form Register information of a citizen (mail, password, personal information)
     * @return Dto containing user's authentication's token
     */
    @PreAuthorize("isAnonymous()")
    @PostMapping("/register/citizen")
    public ResponseEntity<UserTokenResponse> registerAsCitizen(
            @Valid @RequestBody RegisterRequest form
    ){
        return ResponseEntity.ok(userService.register(form, Role.CITIZEN));
    }

    /**
     * Allows a lawyer to register
     * @param form Register information of a lawyer (mail, password, personal information)
     * @return Dto containing user's authentication's token
     */
    @PreAuthorize("isAnonymous()")
    @PostMapping("/register/lawyer")
    public ResponseEntity<UserTokenResponse> registerAsLawyer(
            @Valid @RequestBody RegisterRequest form
    ){
        return ResponseEntity.ok(userService.register(form, Role.LAWYER));
    }

}
