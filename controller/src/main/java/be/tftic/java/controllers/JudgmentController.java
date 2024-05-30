package be.tftic.java.controllers;

import be.tftic.java.bll.services.JudgmentService;
import be.tftic.java.common.models.requests.filter.JudgmentFilterRequest;
import be.tftic.java.common.models.requests.update.JudgmentUpdateRequest;
import be.tftic.java.common.models.responses.JudgmentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôlleur Rest pour la gestion des jugements.
 *
 * @RestController indique que cette classe est un Contrôleur REST.
 * @RequiredArgsConstructor indique que le compilateur doit générer un constructeur avec les arguments requis.
 * @RequestMapping("/jugement") indique que les requêtes HTTP qui correspondent à cette annotation seront traitées par cette classe.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/judgment")
public class JudgmentController {

    private final JudgmentService judgmentService;

    /**
     * Traite une requête HTTP pour récupérer les jugements associé à une plainte.
     *
     * @PreAuthorize("hasAuthority('AGENT')") indique que cette méthode ne peut être appelée qui si l'utilisateur à l'autorité 'AGENT'.
     * @GetMapping("/{id:\\d+}") indique que cette méthode traite les requêtes HTTP GET envoyé à l'URL ("/jugement/{id} où id est un nombre entier.
     *
     * @param id l'identifiant de la plainte repris dans @PathVariable.
     * @return une réponse HTTP avec une liste des jugements associé à la plainte.
     */
    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<List<JudgmentResponse>> getAllForComplaint(@PathVariable Long id){
        return ResponseEntity.ok(judgmentService.findAllForComplaint(id));
    }

    /**
     * Traite une requête HTTP pour récupérer les jugements en fonction des critères de filtrage.
     *
     * @PreAuthorize("hasAuthority('AGENT')") indique que cette méthode ne peut être appelée que si l'utilisateur à l'autorité 'AGENT'.
     * @GetMapping("/filter") indique que cette méthode traite une requête HTTP GET envoyé à l'URL ("/jugement/filter")
     *
     * @param filter les critères de filtrage
     * @return une réponse HTTP avec une liste des jugements qui correspondent aux critères de filtrage.
     */
    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping("/filter")
    public ResponseEntity<List<JudgmentResponse>> getWithCriteria(@RequestBody @Valid JudgmentFilterRequest filter){
       //TODO : Passer le filtre en paramètre
        return ResponseEntity.ok(judgmentService.findWithCriteria(filter.getComplaintId(),
                                                                filter.getNumberFileComplaint(),
                                                                filter.getDateLowerBound(),
                                                                filter.getDateUpperBound(),
                                                                filter.getKeywords(),
                                                                filter.getDecision()));
    }

    /**
     * Traite une requête HTTP PUT pour clôturer un jugement
     *
     * @PreAuthorize("hasAuthority('AGENT')") indique que cette méthode ne peut être appelée que si l'utilisateur à l'autorité 'AGENT'
     * @PutMapping("/cloture") indique que cette méthode traite une requête HTTP PUT envoyée à l'URL "/jugement/cloture".
     *
     * @param judgment les informations de mise à jour du jugement.
     * @RequestBody indique une conversion de la requête HTTP en objet 'JugementUpdateRequest'.
     * @Valid si les informations de mise à jour du jugement sont invalides.
     * @return une réponse HTTP avec un code de statut 204 (No Content) si la mise à jour a réussi.
     */
    @PreAuthorize("hasAuthority('AGENT')")
    @PutMapping("/closed")
    public ResponseEntity<Void> closedJudgment(@RequestBody @Valid JudgmentUpdateRequest judgment){
        judgmentService.closedJudgment(judgment);
        return ResponseEntity.noContent().build();
    }

}
