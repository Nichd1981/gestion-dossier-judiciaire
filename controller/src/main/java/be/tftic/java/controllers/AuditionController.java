package be.tftic.java.controllers;

import be.tftic.java.bll.services.AuditionService;
import be.tftic.java.common.models.requests.AuditionFilterRequest;
import be.tftic.java.common.models.responses.AuditionShortResponse;
import be.tftic.java.common.models.responses.PlainteShortResponse;
import be.tftic.java.domain.entities.Audition;
import be.tftic.java.domain.entities.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.xml.UtilNamespaceHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour gérer les opérations liées aux auditions.
 *
 * Les différentes opérations incluent la récupération des auditions
 * par plainte, la récupération de toutes les auditions, et la recherche
 * des auditions selon des critères spécifiques pour un utilisateur citoyen.
 *
 */
@RestController
@RequestMapping("/audition")
@RequiredArgsConstructor
public class AuditionController {

	private final AuditionService auditionService;

	/**
	 * Récupère toutes les auditions associées à une plainte donnée.
	 *
	 * @param id l'identifiant de la plainte
	 * @return une réponse contenant la liste des auditions correspondantes sous forme d'AuditionShortResponse
	 */
	@PreAuthorize("hasAuthority('AGENT', 'AVOCAT')")
	@GetMapping("/{id: \\d+}")
	public ResponseEntity<List<AuditionShortResponse>> getAuditionByPlainte(@PathVariable Long id) {

		List<AuditionShortResponse> auditionsDTOS = auditionService.findAllAudition(id)
				.stream()
				.map(AuditionShortResponse::fromEntity)
				.toList();

		return ResponseEntity.ok(auditionsDTOS);
	}

	/**
	 * Récupère toutes les auditions disponibles.
	 *
	 * @return une réponse contenant la liste de toutes les auditions sous forme d'AuditionShortResponse
	 */
	@PreAuthorize("hasAuthority('AGENT')")
	@GetMapping
	public ResponseEntity<List<AuditionShortResponse>> getAll() {

		List<AuditionShortResponse> dtos = auditionService.findAll()
				.stream()
				.map(AuditionShortResponse::fromEntity)
				.toList();

		return ResponseEntity.ok(dtos);
	}

	/**
	 * Recherche des auditions selon des critères spécifiés pour un utilisateur citoyen.
	 *
	 * @param authentication l'objet Authentication contenant les informations de l'utilisateur authentifié
	 * @param f l'objet AuditionFilterRequest contenant les critères de recherche
	 * @return une réponse contenant la liste des auditions correspondantes sous forme d'AuditionShortResponse
	 */
	@PreAuthorize("hasAuthority('CITOYEN')")
	@GetMapping("/citoyen")
	public ResponseEntity<List<AuditionShortResponse>> getFindAuditionByCriteria(Authentication authentication, @RequestBody AuditionFilterRequest f) {
		Utilisateur c = (Utilisateur) authentication.getPrincipal();
		List<Audition> auditions = auditionService.findAuditionByCriteria(c.getPersonne(),
				f.getDateLowerBound(),
				f.getDateUpperBound(),
				f.getMotCle());
		List<AuditionShortResponse> dtos = auditions.stream().map(AuditionShortResponse::fromEntity).toList();
		return ResponseEntity.ok(dtos);
	}
}
