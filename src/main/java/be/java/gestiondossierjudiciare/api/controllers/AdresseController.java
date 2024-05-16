package be.java.gestiondossierjudiciare.api.controllers;

import be.java.gestiondossierjudiciare.api.forms.AdresseUpdateForm;
import be.java.gestiondossierjudiciare.bll.services.AdresseService;
import be.java.gestiondossierjudiciare.domain.entities.Adresse;
import be.java.gestiondossierjudiciare.domain.entities.Citoyen;
import be.java.gestiondossierjudiciare.domain.entities.Connexion;
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
@RequestMapping("/adresse")
public class AdresseController {

    private final AdresseService adresseService;

    @PreAuthorize("hasAnyAuthority('AGENT','CITOYEN')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Void> updateAdresse(@PathVariable Long id,
                                              @RequestBody @Valid AdresseUpdateForm adresse,
                                              Authentication authentication)
    {
        Connexion c = (Connexion) authentication.getPrincipal();

        if (c.getRole() == Role.CITOYEN){
            Citoyen citoyen = c.getCitoyen();
            Set<Adresse> adresses = citoyen.getAdresses();
            if (adresses.stream().noneMatch(a -> a.getId().equals(id))) {
                // L'adresse que l'on veut modifier n'est pas une des adresses de ce citoyen
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        adresseService.update(id, adresse.toEntity());
        return ResponseEntity.ok().build();

    }

}
