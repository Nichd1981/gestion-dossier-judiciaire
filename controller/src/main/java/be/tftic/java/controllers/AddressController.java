package be.tftic.java.controllers;

import be.tftic.java.bll.services.AddressService;
import be.tftic.java.common.models.requests.update.AddressUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addresService;

    @PreAuthorize("hasAnyAuthority('AGENT','CITIZEN')")
    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Long> updateAddress(@PathVariable Long id,
                                              @RequestBody @Valid AddressUpdateRequest address) throws AccessDeniedException {
        return ResponseEntity.ok(addresService.update(id, address.toEntity()));
    }
}
