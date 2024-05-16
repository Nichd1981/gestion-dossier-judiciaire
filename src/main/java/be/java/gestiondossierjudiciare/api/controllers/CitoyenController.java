package be.java.gestiondossierjudiciare.api.controllers;

import be.java.gestiondossierjudiciare.api.forms.CitoyenUpdateForm;
import be.java.gestiondossierjudiciare.bll.services.CitoyenService;
import be.java.gestiondossierjudiciare.domain.entities.Connexion;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/citoyen")
public class CitoyenController {

    private final CitoyenService citoyenService;

    @PreAuthorize("hasAuthority('AGENT')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updateCitoyen(@PathVariable Long id, @RequestBody @Valid CitoyenUpdateForm citoyen) {

        Long returnId = citoyenService.update(id, citoyen.toEntity());
        return ResponseEntity.ok(returnId);

    }

    @PreAuthorize("hasAuthority('CITOYEN')")
    @PutMapping
    public ResponseEntity<Long> updateCitoyen(@RequestBody @Valid CitoyenUpdateForm citoyen, Authentication auth) {

        Connexion c = (Connexion) auth.getPrincipal();
        Long id = c.getCitoyen().getId();
        Long returnId = citoyenService.update(id, citoyen.toEntity());
        return ResponseEntity.ok(returnId);

    }

}
