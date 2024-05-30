package be.tftic.java.controllers;

import be.tftic.java.bll.services.PersonService;
import be.tftic.java.common.models.requests.update.PersonUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @PreAuthorize("hasAuthority('AGENT')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updatePerson(@PathVariable Long id, @RequestBody @Valid PersonUpdateRequest person) {
        return ResponseEntity.ok(personService.update(id, person.toEntity()));
    }

    @PreAuthorize("hasAuthority('CITIZEN')")
    @PutMapping
    public ResponseEntity<Long> updatePerson(@RequestBody @Valid PersonUpdateRequest person) {
        return ResponseEntity.ok(personService.update(null, person.toEntity()));
    }
}
