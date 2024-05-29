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

/**
 * Controleur  REST pour la gestion des depositions.
 *
 * @RestController Indique que cette classe est un controller REST.
 * @RequestMapping indique que les requetes HTTP qui corespondent a cette onnotation seront traitees par cette classe
 * @RequiredArgsConstructor indique que le compilateur doit generer un constructeur avec les agruments requis.
 */
@RestController
@RequestMapping("/deposition")
@RequiredArgsConstructor
public class DepositionController {

    private final DepositionService depositionService;

    /**
     * @PreAuthorize indique que cette methode est appeler que si l'utilisateur est identifier
     * @GetMapping("/{id:\\d+}") indique que cette methode traite les requetes HTTP GET envoyees a a l'URL "/deposition/{id}", ou {id} est un nombre entier.
     *
     * @param id l'identifiant de la plainte
     * @return une reponse HTTP avec la liste des depositions associer a la plainte
     */

    @PreAuthorize("hasAnyAuthority('CITOYEN', 'AGENT')")
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<List<DepositionShortResponse>> getDepositionByPlainte(@PathVariable Long id) {


        List<DepositionShortResponse> dtos = depositionService.findAllDeposition(id)
                .stream()
                .map(DepositionShortResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    /**
     * Traite une requete HTTP GET pour recuperer les depositions en fonction de critere de filtrage
     * @PreAuthorize("hasAuthority('CITOYEN')") indique que cette methode ne peut etre appeler que que si l'utilisateur a l'autoriter 'CITOYENT'
     * @GetMapping("/filter") indique que cette methode traite les requetes HTTP GET envoyees a l'URL "/deposition/filter"
     *
     * @param f les criteres de filtrage.
     * @return une reponse HTTP avec la liste des depositions qui correspondent aux criteres de filtrage
     */

    @PreAuthorize("hasAuthority('CITOYEN')")
    @GetMapping("/filter")
    public ResponseEntity<List<DepositionShortResponse>> getWithCriteria(@RequestBody DepositionFilterRequest f) {
        return ResponseEntity.ok(depositionService.findByCriteria(f));
    }

}
