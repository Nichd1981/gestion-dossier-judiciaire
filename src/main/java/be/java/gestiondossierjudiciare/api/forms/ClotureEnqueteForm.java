package be.java.gestiondossierjudiciare.api.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClotureEnqueteForm(

        @NotNull
        Long plainteId,

        @NotBlank
        @Pattern(regexp="^(SANS_SUITE|CONTRAVENTION|DELIT|CRIME)$", message = "type is not an accepted value")
        String type
) {
}
