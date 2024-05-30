package be.tftic.java.controllers;

import be.tftic.java.bll.services.PhoneService;
import be.tftic.java.common.models.requests.update.PhoneUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/phone")
public class TelephoneController {

    private final PhoneService phoneService;

    @PreAuthorize("hasAnyAuthority('AGENT','CITIZEN')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updatePhone(@PathVariable Long id,
                                                @RequestBody @Valid PhoneUpdateRequest phone) throws AccessDeniedException {
        return ResponseEntity.ok(phoneService.update(id, phone.toEntity()));
    }
}
