package be.tftic.java.controllers;

import be.tftic.java.bll.services.UtilisateurService;
import be.tftic.java.common.models.requests.LoginForm;
import be.tftic.java.common.models.responses.UtilisateurTokenDTO;
import be.tftic.java.domain.entities.Utilisateur;
import be.tftic.java.il.utils.JwtUtils;
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
