package be.tftic.java.common.models.requests.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JugementUpdateRequest {

    @NotNull
    private Long id;

    @NotBlank
    private String commentaire;

    @Pattern(regexp="^(SANS_SUITE|CONDAMNATION)$", message = "decision is not an accepted value")
    private String decision;
}
