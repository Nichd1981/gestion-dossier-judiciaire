package be.tftic.java.controllers;

import be.tftic.java.bll.services.PersonneService;
import be.tftic.java.common.models.requests.PersonneUpdateRequest;
import be.tftic.java.domain.entities.Utilisateur;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour gérer les opérations liées aux personnes.
 *
 * Les différentes opérations incluent la mise à jour des informations d'une personne
 * soit par un administrateur avec l'identifiant de la personne, soit par un citoyen
 * pour ses propres informations.
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/personne")
public class PersonneController {

    private final PersonneService personneService;

    /**
     * Met à jour les informations d'une personne spécifiée par son identifiant.
     * @PutMapping("/{id:\\d+}") indique que cette methode traite une requette HTTP PUT envoyer
     * @param id l'identifiant de la personne à mettre à jour
     * @param personne l'objet PersonneUpdateRequest contenant les nouvelles informations de la personne
     * @return une réponse contenant l'identifiant de la personne mise à jour
     */
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updatePersonne(@PathVariable Long id, @RequestBody @Valid PersonneUpdateRequest personne) {

        Long returnId = personneService.update(id, personne.toEntity());
        return ResponseEntity.ok(returnId);

    }

    /**
     * Met à jour les informations de la personne associée à l'utilisateur citoyen authentifié.
     *
     * @param personne l'objet PersonneUpdateRequest contenant les nouvelles informations de la personne
     * @param auth l'objet Authentication contenant les informations de l'utilisateur authentifié
     * @return une réponse contenant l'identifiant de la personne mise à jour
     */
    @PreAuthorize("hasAuthority('CITOYEN')")
    @PutMapping
    public ResponseEntity<Long> updatePersonne(@RequestBody @Valid PersonneUpdateRequest personne, Authentication auth) {

        Utilisateur c = (Utilisateur) auth.getPrincipal();
        Long id = c.getPersonne().getId();
        Long returnId = personneService.update(id, personne.toEntity());
        return ResponseEntity.ok(returnId);

    }

}

