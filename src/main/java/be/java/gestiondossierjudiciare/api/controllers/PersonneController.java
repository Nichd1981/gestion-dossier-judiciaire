package be.java.gestiondossierjudiciare.api.controllers;

import be.java.gestiondossierjudiciare.api.forms.PersonneUpdateForm;
import be.java.gestiondossierjudiciare.bll.services.PersonneService;
import be.java.gestiondossierjudiciare.domain.entities.Utilisateur;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personne")
public class PersonneController {

    private final PersonneService personneService;

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updatePersonne(@PathVariable Long id, @RequestBody @Valid PersonneUpdateForm personne) {

        Long returnId = personneService.update(id, personne.toEntity());
        return ResponseEntity.ok(returnId);

    }

    @PreAuthorize("hasAuthority('CITOYEN')")
    @PutMapping
    public ResponseEntity<Long> updatePersonne(@RequestBody @Valid PersonneUpdateForm personne, Authentication auth) {

        Utilisateur c = (Utilisateur) auth.getPrincipal();
        Long id = c.getPersonne().getId();
        Long returnId = personneService.update(id, personne.toEntity());
        return ResponseEntity.ok(returnId);

    }

}
