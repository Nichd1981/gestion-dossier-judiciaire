package be.java.gestiondossierjudiciare.api.controllers;

import be.java.gestiondossierjudiciare.api.dtos.ConnexionTokenDTO;
import be.java.gestiondossierjudiciare.api.forms.LoginForm;
import be.java.gestiondossierjudiciare.bll.services.ConnexionService;
import be.java.gestiondossierjudiciare.domain.entities.Connexion;
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
    private final ConnexionService connexionService;
    private final JwtUtils jwtUtils;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<ConnexionTokenDTO> login(@RequestBody LoginForm form) {
        Connexion connexion = connexionService.login(form.toEntity());
        ConnexionTokenDTO dto = ConnexionTokenDTO.fromEntity(connexion);
        String token = jwtUtils.generateToken(connexion);
        dto.setToken(token);
        return ResponseEntity.ok(dto);
    }
}
