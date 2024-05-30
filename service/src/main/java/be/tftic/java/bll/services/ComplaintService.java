package be.tftic.java.bll.services;

import be.tftic.java.common.models.requests.create.ComplaintCreateRequest;
import be.tftic.java.common.models.requests.filter.ComplaintFilterRequest;
import be.tftic.java.common.models.requests.update.ClosedSurveyRequest;
import be.tftic.java.common.models.responses.ComplaintShortResponse;
import be.tftic.java.domain.entities.Complaint;
import java.util.List;

public interface ComplaintService {

    List<ComplaintShortResponse> findAll();

    Complaint findById(Long id);

    Complaint findByFileNumber(String fileNumber);

    List<ComplaintShortResponse> findByComplainantId();

    List<ComplaintShortResponse> findByPersonConcerned();

    List<ComplaintShortResponse> findByCriteria(ComplaintFilterRequest f);

    Complaint create(ComplaintCreateRequest form);

    void openSurvey(Long id);

    void closedSurvey(ClosedSurveyRequest form);

    List<ComplaintShortResponse> findByComplaintIdWithCriteria(ComplaintFilterRequest f);

}
