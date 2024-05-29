package be.tftic.java.controllers;

import be.tftic.java.bll.services.TelephoneService;
import be.tftic.java.common.models.requests.TelephoneUpdateRequest;
import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Telephone;
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
 * Contrôleur REST pour gérer les opérations liées aux téléphones.
 *
 * Les opérations incluent la mise à jour des informations d'un téléphone,
 * avec des vérifications de permissions selon le rôle de l'utilisateur.
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/telephone")
public class TelephoneController {

    private final TelephoneService telephoneService;

    /**
     * Met à jour les informations d'un téléphone spécifié par son identifiant.
     *
     * Les utilisateurs avec les rôles "AGENT" ou "CITOYEN" sont autorisés à effectuer cette opération.
     * Si l'utilisateur a le rôle "CITOYEN", une vérification supplémentaire est effectuée pour s'assurer
     * que le téléphone à modifier appartient bien à cet utilisateur.
     *
     *
     * @param id l'identifiant du téléphone à mettre à jour
     * @param telephone l'objet TelephoneUpdateRequest contenant les nouvelles informations du téléphone
     * @param authentication l'objet Authentication contenant les informations de l'utilisateur authentifié
     * @return une réponse contenant l'identifiant du téléphone mis à jour ou une réponse avec le statut HTTP FORBIDDEN si l'utilisateur n'a pas le droit de modifier ce téléphone
     */
    @PreAuthorize("hasAnyAuthority('AGENT','CITOYEN')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updateTelephone(@PathVariable Long id,
                                                @RequestBody @Valid TelephoneUpdateRequest telephone,
                                                Authentication authentication)
    {

        Utilisateur c = (Utilisateur) authentication.getPrincipal();

        if (c.getRole() == Role.CITOYEN) {
            Personne personne = c.getPersonne();
            Set<Telephone> tels = personne.getTelephones();
            if (tels.stream().noneMatch(t -> t.getId().equals(id))) {
                // Le numéro de téléphone que l'on veut modifier n'appartient pas à ce citoyen
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        Long returnId = telephoneService.update(id, telephone.toEntity());
        return ResponseEntity.ok(returnId);

    }

}

