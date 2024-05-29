package be.tftic.java.controllers;

import be.tftic.java.bll.services.JugementService;
import be.tftic.java.common.models.requests.filter.JugementFilterRequest;
import be.tftic.java.common.models.requests.update.JugementUpdateRequest;
import be.tftic.java.common.models.responses.JugementResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jugement")
public class JugementController {

    private final JugementService jugementService;

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<List<JugementResponse>> getAllForPlainte(@PathVariable Long id){

        List<JugementResponse> dtos = jugementService.findAllForPlainte(id)
                .stream()
                .map(JugementResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/filter")
    public ResponseEntity<List<JugementResponse>> getWithCriteria(@RequestBody @Valid JugementFilterRequest filter){

        List<JugementResponse> dtos = jugementService.findWithCriteria(
                                                        filter.getPlainteId(),
                                                        filter.getNumeroDossierPlainte(),
                                                        filter.getDateLowerBound(),
                                                        filter.getDateUpperBound(),
                                                        filter.getKeywords(),
                                                        filter.getDecision())
                .stream()
                .map(JugementResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @PutMapping("/cloture")
    public ResponseEntity<Void> clotureJugement(@RequestBody @Valid JugementUpdateRequest jugement){
        jugementService.cloturerJugement(jugement);
        return ResponseEntity.noContent().build();
    }

}
