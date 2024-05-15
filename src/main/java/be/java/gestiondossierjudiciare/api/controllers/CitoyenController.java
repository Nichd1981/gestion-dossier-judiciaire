package be.java.gestiondossierjudiciare.api.controllers;

import be.java.gestiondossierjudiciare.api.forms.CitoyenUpdateForm;
import be.java.gestiondossierjudiciare.bll.services.CitoyenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/citoyen")
public class CitoyenController {

    private final CitoyenService citoyenService;

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updateCitoyen(@PathVariable Long id, @Valid CitoyenUpdateForm citoyen) {

        Long returnId = citoyenService.update(id, citoyen.toEntity());
        return ResponseEntity.ok(returnId);

    }

}
