package be.java.gestiondossierjudiciare.api.controllers;

import be.java.gestiondossierjudiciare.api.dtos.PlainteDTO;
import be.java.gestiondossierjudiciare.bll.services.PlainteService;
import be.java.gestiondossierjudiciare.domain.entities.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plainte")
public class PlainteController {
    private final PlainteService plainteService;

    @GetMapping
    @PreAuthorize("hasAuthority('CITOYEN')")
    public ResponseEntity<List<PlainteDTO>> getPlainteByPlaignantId(Authentication authentication) {
        Utilisateur c = (Utilisateur) authentication.getPrincipal();

        List<PlainteDTO> plaintes = plainteService.findByPlaignantId(c.getPersonne().getId()).stream()
                .map(PlainteDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(plaintes);
    }
}
