package be.tftic.java.common.models.requests;

import be.tftic.java.common.annotations.CombinedNotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@CombinedNotNull(
        fields = {"plainteId","numeroDossierPlainte"},
        message="plainteId ou numeroDossierPlainte doit être encodé"
)
public class JugementFilterRequest {


    private Long plainteId;
    private String numeroDossierPlainte;

    private LocalDate dateLowerBound;
    private LocalDate dateUpperBound;
    private String keywords;
    private String decision;

}
