package be.tftic.java.bll.services;

import be.tftic.java.common.models.requests.filter.DepositionFilterRequest;
import be.tftic.java.common.models.responses.DepositionShortResponse;
import be.tftic.java.domain.entities.Deposition;

import java.util.List;

public interface DepositionService {

    List<DepositionShortResponse> findAllDeposition(Long id);
    List<DepositionShortResponse> findByCriteria(DepositionFilterRequest f);

}
