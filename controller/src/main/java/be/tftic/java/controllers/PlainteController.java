package be.tftic.java.controllers;

import be.tftic.java.bll.services.PlainteService;
import be.tftic.java.common.models.requests.update.ClotureEnqueteRequest;
import be.tftic.java.common.models.requests.create.PlainteCreateRequest;
import be.tftic.java.common.models.requests.filter.PlainteFilterRequest;
import be.tftic.java.common.models.responses.PlainteDetailResponse;
import be.tftic.java.common.models.responses.PlainteShortResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return ResponseEntity.ok(plainteService.findAll());
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<PlainteDetailResponse> getOne(@PathVariable Long id){
        return ResponseEntity.ok(PlainteDetailResponse.fromEntity(plainteService.findById(id)));
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/filter")
    public ResponseEntity<List<PlainteShortResponse>> getWithCriteria(@RequestBody PlainteFilterRequest f) {
        return ResponseEntity.ok(plainteService.findByCriteria(f));
    }

    @PreAuthorize("hasAuthority('CITOYEN')")
    @GetMapping("/citoyen")
    public ResponseEntity<List<PlainteShortResponse>> getPlainteByPlaignantId() {
        return ResponseEntity.ok(plainteService.findByPlaignantId());
    }

    @PreAuthorize("hasAuthority('CITOYEN')")
    @GetMapping("/citoyen/concerne")
    public ResponseEntity<List<PlainteShortResponse>> getFindByPersonneConcernee(){
        return ResponseEntity.ok(plainteService.findByPersonneConcernee());
    }

    @PreAuthorize("hasAuthority('CITOYEN')")
    @GetMapping("/citoyen/filter")
    public ResponseEntity<List<PlainteShortResponse>> getFindByPlaignantIdWithCriteria(@RequestBody PlainteFilterRequest f){
        return ResponseEntity.ok(plainteService.findByPlaignantIdWithCriteria(f));
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @PostMapping()
    public ResponseEntity<PlainteDetailResponse> createOne(@RequestBody @Valid PlainteCreateRequest form){
        return ResponseEntity.created(URI.create("/plainte/" + plainteService.create(form).getId())).build();
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
