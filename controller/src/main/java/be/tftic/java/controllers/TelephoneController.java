package be.tftic.java.controllers;

import be.tftic.java.bll.services.TelephoneService;
import be.tftic.java.common.models.requests.TelephoneUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/telephone")
public class TelephoneController {

    private final TelephoneService telephoneService;

    @PreAuthorize("hasAnyAuthority('AGENT','CITOYEN')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updateTelephone(@PathVariable Long id,
                                                @RequestBody @Valid TelephoneUpdateRequest telephone) throws AccessDeniedException {
        return ResponseEntity.ok(telephoneService.update(id, telephone.toEntity()));
    }
}
