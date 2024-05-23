package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Deposition;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class DepositionShortResponse {
    LocalDate date;
    String deposition;

    public static DepositionShortResponse fromEntity(Deposition deposition) {
        return DepositionShortResponse.builder()
                .date(deposition.getDateDeposition())
                .deposition(deposition.getDeposition())
                .build();
    }
}
