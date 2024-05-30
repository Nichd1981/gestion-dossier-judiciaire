package be.tftic.java.bll.services;

import be.tftic.java.common.models.requests.create.AuditionCreateRequest;
import be.tftic.java.common.models.requests.filter.AuditionFilterRequest;
import be.tftic.java.common.models.responses.AuditionShortResponse;
import java.util.List;


public interface AuditionService {

	void create(AuditionCreateRequest request);

	List<AuditionShortResponse> findAllAudition(Long id);

	List<AuditionShortResponse> findAll();

	List<AuditionShortResponse> findAuditionByCriteria(AuditionFilterRequest f);
}
