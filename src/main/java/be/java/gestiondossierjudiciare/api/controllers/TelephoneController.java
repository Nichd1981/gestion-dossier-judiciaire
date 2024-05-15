package be.java.gestiondossierjudiciare.api.controllers;

import be.java.gestiondossierjudiciare.api.forms.TelephoneUpdateForm;
import be.java.gestiondossierjudiciare.bll.services.TelephoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/telephone")
public class TelephoneController {

    private final TelephoneService telephoneService;

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updateTelephone(@PathVariable Long id, @RequestBody @Valid TelephoneUpdateForm telephone) {

        Long returnId = telephoneService.update(id, telephone.toEntity());
        return ResponseEntity.ok(returnId);

    }

}
