package be.tftic.java.controllers;

import be.tftic.java.bll.services.PersonneService;
import be.tftic.java.common.models.requests.update.PersonneUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personne")
public class PersonneController {

    private final PersonneService personneService;

    @PreAuthorize("hasAuthority('AGENT')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updatePersonne(@PathVariable Long id, @RequestBody @Valid PersonneUpdateRequest personne) {
        return ResponseEntity.ok(personneService.update(id, personne.toEntity()));
    }

    @PreAuthorize("hasAuthority('CITOYEN')")
    @PutMapping
    public ResponseEntity<Long> updatePersonne(@RequestBody @Valid PersonneUpdateRequest personne) {
        return ResponseEntity.ok(personneService.update(null, personne.toEntity()));
    }
}
