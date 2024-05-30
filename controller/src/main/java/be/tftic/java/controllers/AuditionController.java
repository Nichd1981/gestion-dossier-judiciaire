package be.tftic.java.controllers;

import be.tftic.java.bll.services.AuditionService;
import be.tftic.java.common.models.requests.create.AuditionCreateRequest;
import be.tftic.java.common.models.requests.filter.AuditionFilterRequest;
import be.tftic.java.common.models.responses.AuditionShortResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Contrôleur REST pour gérer les opérations liées aux auditions.
 * Les différentes opérations incluent la récupération des auditions
 * par plainte, la récupération de toutes les auditions, et la recherche
 * des auditions selon des critères spécifiques pour un utilisateur citoyen.
 */
@RestController
@RequestMapping("/audition")
@RequiredArgsConstructor
public class AuditionController {

	private final AuditionService auditionService;

	/**
	 * Récupère toutes les auditions associées à une plainte donnée.
	 * @return une réponse contenant la liste des auditions correspondantes sous forme d'AuditionShortResponse
	 */
	@PreAuthorize("hasAuthority('AGENT')")
	@PostMapping()
	public ResponseEntity<Void> createAudition(@RequestBody @Valid AuditionCreateRequest request){
		auditionService.create(request);
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("hasAnyAuthority('AGENT', 'LAWYER')")
	@GetMapping("/{id:\\d+}")
	public ResponseEntity<List<AuditionShortResponse>> getAuditionByComplaint(@PathVariable Long id) {
		return ResponseEntity.ok(auditionService.findAllAudition(id));
	}

	/**
	 * Récupère toutes les auditions disponibles.
	 * @return une réponse contenant la liste de toutes les auditions sous forme d'AuditionShortResponse
	 */
	@PreAuthorize("hasAuthority('AGENT')")
	@GetMapping
	public ResponseEntity<List<AuditionShortResponse>> getAll(){
		return ResponseEntity.ok(auditionService.findAll());
	}

	/**
	 * Recherche des auditions selon des critères spécifiés pour un utilisateur citoyen.
	 * @param f l'objet AuditionFilterRequest contenant les critères de recherche
	 * @return une réponse contenant la liste des auditions correspondantes sous forme d'AuditionShortResponse
	 */
	@PreAuthorize("hasAuthority('CITIZEN')")
	@GetMapping("/citizen")
	public ResponseEntity<List<AuditionShortResponse>> getFindAuditionByCriteria(@RequestBody AuditionFilterRequest f){
		return ResponseEntity.ok(auditionService.findAuditionByCriteria(f));
	}

}
