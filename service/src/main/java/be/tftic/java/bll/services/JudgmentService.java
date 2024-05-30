package be.tftic.java.bll.services;

import be.tftic.java.common.models.requests.update.JudgmentUpdateRequest;
import be.tftic.java.common.models.responses.JudgmentResponse;
import java.time.LocalDate;
import java.util.List;

public interface JudgmentService {

    void create(Long complaintId);

    List<JudgmentResponse> findAllForComplaint(Long complaintId);

    List<JudgmentResponse> findWithCriteria(Long complaintId, String fileNumber, LocalDate lowerBound, LocalDate upperBound, String keyWord, String decision);

    void closedJudgment(JudgmentUpdateRequest judgment);

}
