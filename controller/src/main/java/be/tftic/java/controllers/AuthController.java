package be.tftic.java.controllers;

import be.tftic.java.bll.services.UtilisateurService;
import be.tftic.java.common.models.requests.LoginRequest;
import be.tftic.java.common.models.responses.UtilisateurTokenResponse;
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
    private final UtilisateurService utilisateurService;

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
    public ResponseEntity<UtilisateurTokenResponse> login(@RequestBody LoginRequest form) {
        return ResponseEntity.ok(utilisateurService.login(form.toEntity()));
    }
}
