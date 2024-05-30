package be.tftic.java.common.models.requests.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClosedSurveyRequest(

        @NotNull
        Long complaintId,

        @NotBlank
        @Pattern(regexp="^(DISMISSED|INFRINGEMENT|OFFENSE|CRIME)$", message = "type is not an accepted value")
        String type
) {
}
