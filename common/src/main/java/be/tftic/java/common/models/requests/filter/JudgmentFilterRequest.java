package be.tftic.java.common.models.requests.filter;

import be.tftic.java.common.annotations.CombinedNotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@CombinedNotNull(
        fields = {"complaintId","numberFileComplaint"},
        message="L'id de la plainte ou le numéro du dossier de plainte doit être encodé"

)
public class JudgmentFilterRequest {

    private Long complaintId;
    private String numberFileComplaint;

    private LocalDate dateLowerBound;
    private LocalDate dateUpperBound;
    private String keywords;
    private String decision;

}
