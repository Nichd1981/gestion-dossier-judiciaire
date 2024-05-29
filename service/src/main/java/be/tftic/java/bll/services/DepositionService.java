package be.tftic.java.bll.services;

import be.tftic.java.common.models.requests.DepositionFilterRequest;
import be.tftic.java.common.models.responses.DepositionShortResponse;
import be.tftic.java.domain.entities.Deposition;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public interface DepositionService {

    List<DepositionShortResponse> findAllDeposition(Long id);
    List<DepositionShortResponse> findByCriteria(DepositionFilterRequest f);

}
