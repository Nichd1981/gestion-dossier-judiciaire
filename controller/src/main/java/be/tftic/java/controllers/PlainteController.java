package be.tftic.java.controllers;

import be.tftic.java.bll.services.PlainteService;
import be.tftic.java.common.models.requests.ClotureEnqueteRequest;
import be.tftic.java.common.models.requests.PlainteCreateRequest;
import be.tftic.java.common.models.requests.PlainteFilterRequest;
import be.tftic.java.common.models.responses.PlainteDetailResponse;
import be.tftic.java.common.models.responses.PlainteShortResponse;
import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.entities.Utilisateur;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/plainte")
@RequiredArgsConstructor
public class PlainteController {

    private final PlainteService plainteService;


    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping
    public ResponseEntity<List<PlainteShortResponse>> getAll(){

        List<PlainteShortResponse> dtos = plainteService.findAll()
                .stream()
                .map(PlainteShortResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<PlainteDetailResponse> getOne(@PathVariable Long id){

        PlainteDetailResponse dto = PlainteDetailResponse.fromEntity(plainteService.findById(id));

        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/filter")
    public ResponseEntity<List<PlainteShortResponse>> getWithCriteria(@RequestBody PlainteFilterRequest f) {

        List<PlainteShortResponse> plaintes = plainteService.findByCriteria(f.getNumeroDossier(),f.getDateLowerBound(),f.getDateUpperBound(), f.getStatut())
                .stream()
                .map(PlainteShortResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(plaintes);
    }

    @PreAuthorize("hasAuthority('CITOYEN')")
    @GetMapping("/citoyen")
    public ResponseEntity<List<PlainteShortResponse>> getPlainteByPlaignantId(Authentication authentication) {
        Utilisateur c = (Utilisateur) authentication.getPrincipal();

        List<PlainteShortResponse> dtos = plainteService.findByPlaignantId(c.getPersonne().getId()).stream()
                .map(PlainteShortResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PreAuthorize("hasAuthority('CITOYEN')")
    @GetMapping("/citoyen/concerne")
    public ResponseEntity<List<PlainteShortResponse>> getFindByPersonneConcernee(Authentication authentication){
        Utilisateur c = (Utilisateur) authentication.getPrincipal();

        List<Plainte> plaintes = plainteService.findByPersonneConcernee(c.getPersonne());
        List<PlainteShortResponse> dtos = plaintes.stream()
                                    .map(PlainteShortResponse::fromEntity)
                                    .toList();

        return ResponseEntity.ok(dtos);
    }

    @PreAuthorize("hasAuthority('CITOYEN')")
    @GetMapping("/citoyen/filter")
    public ResponseEntity<List<PlainteShortResponse>> getFindByPlaignantIdWithCriteria(Authentication authentication, @RequestBody PlainteFilterRequest f){
        Utilisateur c = (Utilisateur) authentication.getPrincipal();
        List<Plainte> plaintes = plainteService.findByPlaignantIdWithCriteria(c.getPersonne(),
                                                                                f.getType(),
                                                                                f.getDateUpperBound(),
                                                                                f.getDateLowerBound(),
                                                                                f.getNumeroDossier(),
                                                                                f.getStatut());
        List<PlainteShortResponse> dtos = plaintes.stream().map(PlainteShortResponse::fromEntity).toList();
        return ResponseEntity.ok(dtos);
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @PostMapping()
    public ResponseEntity<PlainteDetailResponse> createOne(@RequestBody @Valid PlainteCreateRequest form){
        Long id = plainteService.create(form).getId();

        return ResponseEntity.created(URI.create("/plainte/"+id)).build();
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Void> ouvrirEnquete(@PathVariable Long id){
        plainteService.ouvrirEnquete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @PutMapping("/cloture")
    public ResponseEntity<Void> cloturerEnquete(@RequestBody @Valid ClotureEnqueteRequest form){
        plainteService.cloturerEnquete(form);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
