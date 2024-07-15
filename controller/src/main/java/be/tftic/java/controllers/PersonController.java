package be.tftic.java.controllers;

import be.tftic.java.bll.services.PersonService;
import be.tftic.java.common.models.requests.update.PersonUpdateRequest;
import be.tftic.java.common.models.responses.PagedResponse;
import be.tftic.java.common.models.responses.PersonDetailResponse;
import be.tftic.java.common.models.responses.PersonShortResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * Contrôleur REST pour gérer les opérations liées aux personnes.
 * Les différentes opérations incluent la mise à jour des informations d'une personne
 * soit par un administrateur avec l'identifiant de la personne, soit par un citoyen
 * pour ses propres informations.
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
@CrossOrigin("*")
public class PersonController {

    private final PersonService personService;

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping
    public ResponseEntity<PagedResponse<PersonShortResponse>> getAll(
            @RequestParam Map<String, String> params,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        return ResponseEntity.ok(personService.getAll(params, page, pageSize));
    }

    @PreAuthorize("hasAuthority('LAWYER')")
    @GetMapping("/lawyer/{lawyerId:\\d+}")
    public ResponseEntity<PagedResponse<PersonShortResponse>> getCustomersByLawyer(
            @PathVariable Long lawyerId,
            @RequestParam Map<String, String> params,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        params.put("lawyerId", String.valueOf(lawyerId));
        return ResponseEntity.ok(personService.getAll(params, page, pageSize));
    }

    /**
     * Met à jour les informations d'une personne spécifiée par son identifiant.
     * @PutMapping("/{id:\\d+}") indique que cette methode traite une requette HTTP PUT envoyer
     * @param id l'identifiant de la personne à mettre à jour
     * @param person l'objet PersonneUpdateRequest contenant les nouvelles informations de la personne
     * @return une réponse contenant l'identifiant de la personne mise à jour
     */
    @PreAuthorize("hasAuthority('AGENT')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updatePerson(@PathVariable Long id, @RequestBody @Valid PersonUpdateRequest person) {
        return ResponseEntity.ok(personService.update(id, person.toEntity()));
    }

    /**
     * Met à jour les informations de la personne associée à l'utilisateur citoyen authentifié.
     * @param person l'objet PersonneUpdateRequest contenant les nouvelles informations de la personne
     * @return une réponse contenant l'identifiant de la personne mise à jour
     */
    @PreAuthorize("hasAuthority('CITIZEN')")
    @PutMapping
    public ResponseEntity<Long> updatePerson(@RequestBody @Valid PersonUpdateRequest person) {
        return ResponseEntity.ok(personService.update(null, person.toEntity()));
    }

    @PreAuthorize("hasAnyAuthority('AGENT', 'CITIZEN', 'LAWYER')")
    @GetMapping("/details")
    public ResponseEntity<PersonDetailResponse> getDetailsPerson() {
        return ResponseEntity.ok(personService.findUserDetails());
    }

    @PreAuthorize("hasAnyAuthority('AGENT', 'ADMIN')")
    @GetMapping("/list/details")
    public ResponseEntity<List<PersonShortResponse>> getListDetailsPerson() {
        return ResponseEntity.ok(personService.getAllDetailsPerson());
    }

    @PreAuthorize("hasAnyAuthority('AGENT', 'ADMIN')")
    @GetMapping("/{id}/details")
    public ResponseEntity<PersonDetailResponse> getDetailsFromListPerson(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findDetailsById(id));
    }

}
