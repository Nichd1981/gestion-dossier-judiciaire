package be.java.gestiondossierjudiciare.api.controllers;

import be.java.gestiondossierjudiciare.api.dtos.PlainteDetailDTO;
import be.java.gestiondossierjudiciare.api.dtos.PlainteShortDTO;
import be.java.gestiondossierjudiciare.api.forms.PlainteFilter;
import be.java.gestiondossierjudiciare.bll.services.PlainteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/filter")
    public ResponseEntity<List<PlainteShortDTO>> getWithCriteria(@RequestBody PlainteFilter f) {

        List<PlainteShortDTO> plaintes = plainteService.findByCriteria(f.getNumeroDossier(),f.getDateLowerBound(),f.getDateUpperBound(), f.getStatut())
                .stream()
                .map(PlainteShortDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(plaintes);
    }

}
