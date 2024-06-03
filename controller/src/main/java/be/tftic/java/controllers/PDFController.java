package be.tftic.java.controllers;

import be.tftic.java.bll.services.PDFGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pdf")
public class PDFController {

    private final PDFGenerationService pdfService;

    @GetMapping("/generate/{id:\\d+}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long id) {
        byte[] pdfContent = pdfService.generatePdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=document.pdf");
        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }

}
