package be.tftic.java.controllers;

import be.tftic.java.bll.services.DepositionService;
import be.tftic.java.common.models.responses.DepositionShortResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
