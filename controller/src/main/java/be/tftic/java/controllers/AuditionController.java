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

@RestController
@RequestMapping("/audition")
@RequiredArgsConstructor
public class AuditionController {

	private final AuditionService auditionService;

	@PreAuthorize("hasAnyAuthority('AGENT', 'AVOCAT')")
	@GetMapping("/{id: \\d+}")
	public ResponseEntity<List<AuditionShortResponse>> getAuditionByPlainte(@PathVariable Long id) {
		return ResponseEntity.ok(auditionService.findAllAudition(id));
	}

	@PreAuthorize("hasAuthority('AGENT')")
	@GetMapping
	public ResponseEntity<List<AuditionShortResponse>> getAll(){
		return ResponseEntity.ok(auditionService.findAll());
	}

	@PreAuthorize("hasAuthority('CITOYEN')")
	@GetMapping("/citoyen")
	public ResponseEntity<List<AuditionShortResponse>> getFindAuditionByCriteria(Authentication authentication, @RequestBody AuditionFilterRequest f){
		return ResponseEntity.ok(auditionService.findAuditionByCriteria(f));
	}

}
