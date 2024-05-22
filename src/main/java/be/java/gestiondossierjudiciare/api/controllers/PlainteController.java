package be.java.gestiondossierjudiciare.api.controllers;

import be.java.gestiondossierjudiciare.api.dtos.PlainteDetailDTO;
import be.java.gestiondossierjudiciare.api.dtos.PlainteShortDTO;
import be.java.gestiondossierjudiciare.api.forms.PlainteCreateForm;
import be.java.gestiondossierjudiciare.api.forms.PlainteFilter;
import be.java.gestiondossierjudiciare.bll.services.PlainteService;
import be.java.gestiondossierjudiciare.domain.entities.Plainte;
import be.java.gestiondossierjudiciare.domain.entities.Utilisateur;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<PlainteShortDTO>> getAll(){

        List<PlainteShortDTO> dtos = plainteService.findAll()
                .stream()
                .map(PlainteShortDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<PlainteDetailDTO> getOne(@PathVariable Long id){
        PlainteDetailDTO dto = PlainteDetailDTO.fromEntity(plainteService.findById(id));

        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/filter")
    public ResponseEntity<List<PlainteShortDTO>> getWithCriteria(@RequestBody PlainteFilter f) {

        List<PlainteShortDTO> plaintes = plainteService.findByCriteria(f.getNumeroDossier(),f.getDateLowerBound(),f.getDateUpperBound(), f.getStatut())
                .stream()
                .map(PlainteShortDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(plaintes);
    }

    @PreAuthorize("hasAuthority('CITOYEN')")
    @GetMapping("/citoyen")
    public ResponseEntity<List<PlainteShortDTO>> getPlainteByPlaignantId(Authentication authentication) {
        Utilisateur c = (Utilisateur) authentication.getPrincipal();

        List<PlainteShortDTO> dtos = plainteService.findByPlaignantId(c.getPersonne().getId()).stream()
                .map(PlainteShortDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PreAuthorize("hasAuthority('CITOYEN')")
    @GetMapping("/citoyen/concerne")
    public ResponseEntity<List<PlainteShortDTO>> getFindByPersonneConcernee(Authentication authentication){
        Utilisateur c = (Utilisateur) authentication.getPrincipal();

        List<Plainte> plaintes = plainteService.findByPersonneConcernee(c.getPersonne());
        List<PlainteShortDTO> dtos = plaintes.stream()
                                    .map(PlainteShortDTO::fromEntity)
                                    .toList();

        return ResponseEntity.ok(dtos);
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @PostMapping()
    public ResponseEntity<PlainteDetailDTO> createOne(@RequestBody @Valid PlainteCreateForm form){
        Long id = plainteService.create(form).getId();

        return ResponseEntity.created(URI.create("/plainte/"+id)).build();
    }

}
