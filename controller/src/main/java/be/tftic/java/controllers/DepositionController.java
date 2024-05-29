package be.tftic.java.controllers;

import be.tftic.java.bll.services.DepositionService;
import be.tftic.java.common.models.requests.filter.DepositionFilterRequest;
import be.tftic.java.common.models.responses.DepositionShortResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return ResponseEntity.ok(depositionService.findAllDeposition(id));
    }

    @PreAuthorize("hasAuthority('CITOYEN')")
    @GetMapping("/filter")
    public ResponseEntity<List<DepositionShortResponse>> getWithCriteria(@RequestBody DepositionFilterRequest f) {
        return ResponseEntity.ok(depositionService.findByCriteria(f));
    }

}
