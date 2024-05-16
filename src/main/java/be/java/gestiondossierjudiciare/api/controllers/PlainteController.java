package be.java.gestiondossierjudiciare.api.controllers;

import be.java.gestiondossierjudiciare.api.dtos.PlainteDetailDTO;
import be.java.gestiondossierjudiciare.api.dtos.PlainteListDTO;
import be.java.gestiondossierjudiciare.bll.services.PlainteService;
import be.java.gestiondossierjudiciare.domain.entities.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/plainte")
@RequiredArgsConstructor
public class PlainteController {
    private final PlainteService plainteService;

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/agent")
    public ResponseEntity<List<PlainteListDTO>> getAll(){

        List<PlainteListDTO> dtos = plainteService.findAll()
                .stream()
                .map(PlainteListDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/citoyen")
    @PreAuthorize("hasAuthority('CITOYEN')")
    public ResponseEntity<List<PlainteListDTO>> getPlainteByPlaignantId(Authentication authentication) {
        Utilisateur c = (Utilisateur) authentication.getPrincipal();

        List<PlainteListDTO> plaintes = plainteService.findByPlaignantId(c.getPersonne().getId()).stream()
                .map(PlainteListDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(plaintes);
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<PlainteDetailDTO> getOne(@PathVariable Long id){

        PlainteDetailDTO dto = PlainteDetailDTO.fromEntity(plainteService.findById(id));

        return ResponseEntity.ok(dto);
    }
}
