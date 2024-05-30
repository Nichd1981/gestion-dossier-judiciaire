package be.tftic.java.controllers;

import be.tftic.java.bll.services.AddressService;
import be.tftic.java.common.models.requests.update.AddressUpdateRequest;
import be.tftic.java.domain.entities.Address;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addresService;

    /**
     * Met a jour une adresse existante.
     * @param id L'identifiant de l'adresse mettre a jour.
     * @param address Les nouvelles information de l'adresse
     * @return une reponse HTTP vide avec un code status 200(OK) si la mise a reussi, ou un code de statut 403 (Interdit) si l'utilisateur n'est pas autorise a modifier
     *
     * @throws IllegalArgumentException si l'identifiant de l'adresse est invalide
     * @see AddressService#update(Long, Address)
     */
    @PreAuthorize("hasAnyAuthority('AGENT','CITIZEN')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updateAddress(@PathVariable Long id,
                                              @RequestBody @Valid AddressUpdateRequest address) throws AccessDeniedException {
        return ResponseEntity.ok(addresService.update(id, address.toEntity()));
    }
}
