package be.java.gestiondossierjudiciare.api.controllers;

import be.java.gestiondossierjudiciare.api.dtos.UtilisateurTokenDTO;
import be.java.gestiondossierjudiciare.api.forms.LoginForm;
import be.java.gestiondossierjudiciare.bll.services.UtilisateurService;
import be.java.gestiondossierjudiciare.domain.entities.Utilisateur;
import be.java.gestiondossierjudiciare.il.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UtilisateurService utilisateurService;
    private final JwtUtils jwtUtils;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<UtilisateurTokenDTO> login(@RequestBody LoginForm form) {
        Utilisateur utilisateur = utilisateurService.login(form.toEntity());
        UtilisateurTokenDTO dto = UtilisateurTokenDTO.fromEntity(utilisateur);
        String token = jwtUtils.generateToken(utilisateur);
        dto.setToken(token);
        return ResponseEntity.ok(dto);
    }
}
