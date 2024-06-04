package be.tftic.java.controllers;

import be.tftic.java.bll.services.impls.PdfServiceImpl;
import be.tftic.java.common.models.requests.create.AuditionCreateRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/PDF")
public class PdfController {

	private final PdfServiceImpl pdfServiceImpl;

	public PdfController(PdfServiceImpl pdfGenerator) {
		this.pdfServiceImpl = pdfGenerator;
	}

	@PreAuthorize("hasAnyAuthority('AGENT','CITIZEN')")
	@GetMapping("/generate-pdf")
	public ResponseEntity<Void> generatePdf(@RequestBody AuditionCreateRequest audition){
		pdfServiceImpl.generatePdf(audition.toEntity());

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=document.pdf");
		return new ResponseEntity<>(null, headers, HttpStatus.OK);
	}

}
