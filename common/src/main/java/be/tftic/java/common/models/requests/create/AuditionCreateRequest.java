package be.tftic.java.common.models.requests.create;

import be.tftic.java.domain.entities.Audition;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AuditionCreateRequest(
        @NotNull
        LocalDateTime auditionDate,
        @NotNull
        String roomNumber,
        @NotNull
        Long citizenId,
        Long lawyerId,
        @NotNull
        Long complaintId
) {

    public Audition toEntity(){
        return Audition.builder()
                .dateAudition(auditionDate)
                .roomNumberAudition(roomNumber)
                .build();
    }

}
