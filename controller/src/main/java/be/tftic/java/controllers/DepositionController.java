package be.tftic.java.controllers;

import be.tftic.java.bll.services.DepositionService;
import be.tftic.java.common.models.requests.DepositionFilterRequest;
import be.tftic.java.common.models.responses.DepositionShortResponse;
import be.tftic.java.domain.entities.Deposition;
import be.tftic.java.domain.entities.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deposition")
@RequiredArgsConstructor
public class DepositionController {

    private final DepositionService depositionService;

    @PreAuthorize("hasAnyAuthority('CITOYEN', 'AGENT')")
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<List<DepositionShortResponse>> getDepositionByPlainte(@PathVariable Long id) {


        List<DepositionShortResponse> dtos = depositionService.findAllDeposition(id)
                .stream()
                .map(DepositionShortResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PreAuthorize("hasAuthority('CITOYEN')")
    @GetMapping("/filter")
    public ResponseEntity<List<DepositionShortResponse>> getWithCriteria(@RequestBody DepositionFilterRequest f) {
        return ResponseEntity.ok(depositionService.findByCriteria(f));
    }

}
