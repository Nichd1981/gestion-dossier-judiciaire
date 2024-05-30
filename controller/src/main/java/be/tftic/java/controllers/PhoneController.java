package be.tftic.java.controllers;

import be.tftic.java.bll.services.PhoneService;
import be.tftic.java.common.models.requests.update.PhoneUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/phone")
public class PhoneController {

    private final PhoneService phoneService;

    /**
     * Met à jour les informations d'un téléphone spécifié par son identifiant.
     * Les utilisateurs avec les rôles "AGENT" ou "CITOYEN" sont autorisés à effectuer cette opération.
     * Si l'utilisateur a le rôle "CITOYEN", une vérification supplémentaire est effectuée pour s'assurer
     * que le téléphone à modifier appartient bien à cet utilisateur.
     *
     *
     * @param id l'identifiant du téléphone à mettre à jour
     * @param phone l'objet TelephoneUpdateRequest contenant les nouvelles informations du téléphone
     * @return une réponse contenant l'identifiant du téléphone mis à jour ou une réponse avec le statut HTTP FORBIDDEN si l'utilisateur n'a pas le droit de modifier ce téléphone
     */
    @PreAuthorize("hasAnyAuthority('AGENT','CITIZEN')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updatePhone(@PathVariable Long id,
                                                @RequestBody @Valid PhoneUpdateRequest phone) throws AccessDeniedException {
        return ResponseEntity.ok(phoneService.update(id, phone.toEntity()));
    }
}
