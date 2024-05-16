package be.java.gestiondossierjudiciare.api.controllers;

import be.java.gestiondossierjudiciare.api.forms.TelephoneUpdateForm;
import be.java.gestiondossierjudiciare.bll.services.TelephoneService;
import be.java.gestiondossierjudiciare.domain.entities.Personne;
import be.java.gestiondossierjudiciare.domain.entities.Telephone;
import be.java.gestiondossierjudiciare.domain.entities.Utilisateur;
import be.java.gestiondossierjudiciare.domain.enums.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/telephone")
public class TelephoneController {

    private final TelephoneService telephoneService;

    @PreAuthorize("hasAnyAuthority('AGENT','CITOYEN')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updateTelephone(@PathVariable Long id,
                                                @RequestBody @Valid TelephoneUpdateForm telephone,
                                                Authentication authentication)
    {

        Utilisateur c = (Utilisateur) authentication.getPrincipal();

        if (c.getRole() == Role.CITOYEN){
            Personne personne = c.getPersonne();
            Set<Telephone> tels = personne.getTelephones();
            if (tels.stream().noneMatch(t -> t.getId().equals(id))) {
                // Le n° de tel que l'on veut modifier n'est pas un de ceux de ce citoyen
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        Long returnId = telephoneService.update(id, telephone.toEntity());
        return ResponseEntity.ok(returnId);

    }

}
