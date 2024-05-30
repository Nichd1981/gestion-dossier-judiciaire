package be.tftic.java.controllers;

import be.tftic.java.bll.services.ComplaintService;
import be.tftic.java.common.models.requests.filter.ComplaintFilterRequest;
import be.tftic.java.common.models.requests.update.ClosedSurveyRequest;
import be.tftic.java.common.models.requests.create.ComplaintCreateRequest;
import be.tftic.java.common.models.responses.ComplaintDetailResponse;
import be.tftic.java.common.models.responses.ComplaintShortResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

/**
 *  * Contrôleur REST pour la gestion des plaintes.
 *
 * @RestController indique que cette classe est un Contrôleur REST.
 * @RequiredArgsConstructor indique que le compilateur doit générer un constructeur avec les arguments requis.
 * @RequestMapping("/plainte") indique que les requêtes HTTP qui correspondent à cette annotation seront traitées par cette classe.
 */
@RestController
@RequestMapping("/complaint")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    /**
     * Récupère la liste de toutes les plaintes.
     *
     * @PreAuthorize("hasAuthority('AGENT')") Garantit que seuls les utilisateurs ayant le rôle "AGENT" peuvent accéder à cette méthode.
     *
     * @return Une ResponseEntity contenant une liste de PlainteShortResponse représentant toutes les plaintes
     */
    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping
    public ResponseEntity<List<ComplaintShortResponse>> getAll(){
        return ResponseEntity.ok(complaintService.findAll());
    }
    /**
     * Récupère les détails d'une plainte spécifique.
     *
     * @param id L'identifiant unique de la plainte à récupérer
     * @PreAuthorize("hasAuthority('AGENT')") Garantit que seuls les utilisateurs ayant le rôle "AGENT" peuvent accéder à cette méthode.
     *
     * @Param id identifiant de la plainte récupéré dans @PathVariable.
     * @return Une ResponseEntity contenant un objet PlainteDetailResponse représentant les détails de la plainte
     */
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<ComplaintDetailResponse> getOne(@PathVariable Long id){
        return ResponseEntity.ok(ComplaintDetailResponse.fromEntity(complaintService.findById(id)));
    }

    /**
     * Récupère une liste de plaintes filtrées selon différents critères.
     *
     * @PreAuthorize("hasAuthority('AGENT')") Garantit que seuls les utilisateurs ayant le rôle "AGENT" peuvent accéder à cette méthode.
     *
     * @param f L'objet PlainteFilterRequest contenant les critères de filtrage
     * @return Une ResponseEntity contenant une liste de PlainteShortResponse représentant les plaintes correspondant aux critères
     */
    @GetMapping("/agent/filter")
    public ResponseEntity<List<ComplaintShortResponse>> getWithCriteria(@RequestBody ComplaintFilterRequest f) {
        return ResponseEntity.ok(complaintService.findByCriteria(f));
    }

    /**
     * Récupère la liste des plaintes déposées par l'utilisateur authentifié.
     *
     * @PreAuthorize("hasAuthority('CITIZEN')") Garantit que seuls les utilisateurs ayant le rôle "CITOYEN" peuvent accéder à cette méthode.
     *
     * @return Une ResponseEntity contenant une liste de PlainteShortResponse représentant les plaintes déposées par l'utilisateur authentifié
     */
    @PreAuthorize("hasAuthority('CITIZEN')")
    @GetMapping("/citizen")
    public ResponseEntity<List<ComplaintShortResponse>> getComplaintByComplainantId() {
        return ResponseEntity.ok(complaintService.findByComplainantId());
    }
    /**
     * Récupère la liste des plaintes où l'utilisateur authentifié est la personne concernée.
     *
     * @PreAuthorize("hasAuthority('CITIZEN')") Garantit que seuls les utilisateurs ayant le rôle "CITOYEN" peuvent accéder à cette méthode.
     *
     * @return Une ResponseEntity contenant une liste de PlainteShortResponse représentant les plaintes concernant l'utilisateur authentifié
     */
    @PreAuthorize("hasAuthority('CITIZEN')")
    @GetMapping("/citizen/concerned")
    public ResponseEntity<List<ComplaintShortResponse>> getFindByPersonConcerned() {
        return ResponseEntity.ok(complaintService.findByPersonConcerned());
    }

    /**
     * Récupère la liste des plaintes déposées par l'utilisateur authentifié, filtrées selon différents critères.
     *
     * @PreAuthorize("hasAuthority('CITIZEN')") Garantit que seuls les utilisateurs ayant le rôle "CITOYEN" peuvent accéder à cette méthode
     *
     * @param f L'objet PlainteFilterRequest contenant les critères de filtrage
     * @return Une ResponseEntity contenant une liste de PlainteShortResponse représentant les plaintes déposées par l'utilisateur authentifié et correspondant aux critères de filtrage
     */
    @PreAuthorize("hasAuthority('CITIZEN')")
    @GetMapping("/citizen/filter")
    public ResponseEntity<List<ComplaintShortResponse>> getFindByComplainantIdWithCriteria(@RequestBody ComplaintFilterRequest f){
        return ResponseEntity.ok(complaintService.findByComplaintIdWithCriteria(f));
    }

    /**
     * Crée une nouvelle plainte à partir des informations fournies.
     *
     * @PreAuthorize("hasAuthority('AGENT')") Garantit que seuls les utilisateurs ayant le rôle "AGENT" peuvent accéder à cette méthode.
     *
     * @param form L'objet PlainteCreateRequest contenant les informations nécessaires à la création de la plainte
     * @return Une ResponseEntity contenant l'objet PlainteDetailResponse représentant la plainte créée, avec un statut HTTP 201 (Created) et l'URI de la nouvelle ressource dans l'en-tête de la réponse
     */
    @PreAuthorize("hasAuthority('AGENT')")
    @PostMapping()
    public ResponseEntity<ComplaintDetailResponse> createOne(@RequestBody @Valid ComplaintCreateRequest form){
        return ResponseEntity.created(URI.create("/plainte/" + complaintService.create(form).getId())).build();
    }

    /**
     * Ouvre une enquête pour une plainte spécifique.
     *
     * @PreAuthorize("hasAuthority('AGENT')") Garantit que seuls les utilisateurs ayant le rôle "AGENT" peuvent accéder à cette méthode
     *
     * @param id L'identifiant unique de la plainte pour laquelle ouvrir l'enquête
     * @return Une ResponseEntity avec un statut HTTP 204 (No Content) si l'opération est réussie
     */
    @PreAuthorize("hasAuthority('AGENT')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Void> openSurvey(@PathVariable Long id){
        complaintService.openSurvey(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Clôture l'enquête pour une plainte spécifique.
     *
     * @PreAuthorize("hasAuthority('AGENT')") Garantit que seuls les utilisateurs ayant le rôle "AGENT" peuvent accéder à cette méthode
     *
     * @param form L'objet ClotureEnqueteRequest contenant les informations nécessaires pour clôturer l'enquête
     * @return Une ResponseEntity avec un statut HTTP 204 (No Content) si l'opération est réussie
     */
    @PreAuthorize("hasAuthority('AGENT')")
    @PutMapping("/closed")
    public ResponseEntity<Void> closedSurvey(@RequestBody @Valid ClosedSurveyRequest form){
        complaintService.closedSurvey(form);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
