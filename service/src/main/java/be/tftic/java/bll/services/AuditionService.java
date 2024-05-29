package be.tftic.java.bll.services;

import be.tftic.java.common.models.requests.AuditionFilterRequest;
import be.tftic.java.common.models.responses.AuditionShortResponse;
import be.tftic.java.domain.entities.Audition;
import be.tftic.java.domain.entities.Personne;

import java.time.LocalDate;
import java.util.List;


public interface AuditionService {

	List<AuditionShortResponse> findAllAudition(Long id);

	List<AuditionShortResponse> findAll();

	List<AuditionShortResponse> findAuditionByCriteria(AuditionFilterRequest f);
}
