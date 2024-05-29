package be.tftic.java.controllers;

import be.tftic.java.bll.services.AdresseService;
import be.tftic.java.common.models.requests.AdresseUpdateRequest;
import be.tftic.java.domain.entities.Adresse;
import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Utilisateur;
import be.tftic.java.domain.enums.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controleur REST pour gerer les adresses.
 * @author Azzedine
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/adresse")
public class AdresseController {

    private final AdresseService adresseService;

    /**
     * Met a jour une adresse existante.
     * @param id L'identifiant de l'adresse mettre a jour. 
     * @param adresse Les nouvelles information de l'adresse 
     * @param authentication Les informations d'authentification de l'utilisateur 
     * @return une reponse HTTP vide avec un code status 200(OK) si la mise a reussi, ou un code de statut 403 (Interdit) si l'utilisateur n'est pas autorise a modifier 
     *
     * @throws IllegalArgumentException si l'identifiant de l'adresse est invalide 
     * @see AdresseService#update(Long, Adresse) 
     */
    
    @PreAuthorize("hasAnyAuthority('AGENT','CITOYEN')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Void> updateAdresse(@PathVariable Long id,
                                              @RequestBody @Valid AdresseUpdateRequest adresse,
                                              Authentication authentication)
    {
        Utilisateur c = (Utilisateur) authentication.getPrincipal();

        if (c.getRole() == Role.CITOYEN){
            Personne personne = c.getPersonne();
            Set<Adresse> adresses = personne.getAdresses();
            if (adresses.stream().noneMatch(a -> a.getId().equals(id))) {
                // L'adresse que l'on veut modifier n'est pas une des adresses de ce citoyen
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        adresseService.update(id, adresse.toEntity());
        return ResponseEntity.ok().build();

    }

}
