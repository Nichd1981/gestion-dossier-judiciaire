package be.tftic.java.controllers;

import be.tftic.java.bll.services.JugementService;
import be.tftic.java.common.models.requests.JugementFilterRequest;
import be.tftic.java.common.models.requests.JugementUpdateRequest;
import be.tftic.java.common.models.responses.JugementResponse;
import be.tftic.java.common.models.responses.PlainteShortResponse;
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
@RequestMapping("/jugement")
public class JugementController {

    private final JugementService jugementService;

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
    public ResponseEntity<List<JugementResponse>> getAllForPlainte(@PathVariable Long id){

        List<JugementResponse> dtos = jugementService.findAllForPlainte(id)
                .stream()
                .map(JugementResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(dtos);
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

    /**
     * Traite une requête HTTP PUT pour clôturer un jugement
     *
     * @PreAuthorize("hasAuthority('AGENT')") indique que cette méthode ne peut être appelée que si l'utilisateur à l'autorité 'AGENT'
     * @PutMapping("/cloture") indique que cette méthode traite une requête HTTP PUT envoyée à l'URL "/jugement/cloture".
     *
     * @param jugement les informations de mise à jour du jugement.
     * @RequestBody indique une conversion de la requête HTTP en objet 'JugementUpdateRequest'.
     * @Valid si les informations de mise à jour du jugement sont invalides.
     * @return une réponse HTTP avec un code de statut 204 (No Content) si la mise à jour a réussi.
     */
    @PreAuthorize("hasAuthority('AGENT')")
    @PutMapping("/cloture")
    public ResponseEntity<Void> clotureJugement(@RequestBody @Valid JugementUpdateRequest jugement){
        jugementService.cloturerJugement(jugement);
        return ResponseEntity.noContent().build();
    }

}
