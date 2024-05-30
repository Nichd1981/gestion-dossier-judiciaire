package be.tftic.java.common.models.requests.create;

import be.tftic.java.domain.entities.Complaint;
import be.tftic.java.domain.enums.ComplaintStatus;
import java.time.LocalDateTime;
import java.util.List;

public record ComplaintCreateRequest(

        String fileNumber,
        Long idComplainant,
        Long idAgent,
        List<Long> idConcerned

) {

    public Complaint toEntity(){
        return Complaint.builder()
                .fileNumber(fileNumber)
                .complaintDate(LocalDateTime.now())
                .status(ComplaintStatus.REGISTERED)
                .build();
    }

}
