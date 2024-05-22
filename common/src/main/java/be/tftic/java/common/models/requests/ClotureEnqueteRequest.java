package be.tftic.java.common.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClotureEnqueteRequest(

        @NotNull
        Long plainteId,

        @NotBlank
        @Pattern(regexp="^(SANS_SUITE|CONTRAVENTION|DELIT|CRIME)$", message = "type is not an accepted value")
        String type
) {
}
