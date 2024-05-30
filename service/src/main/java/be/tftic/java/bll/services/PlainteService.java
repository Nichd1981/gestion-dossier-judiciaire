package be.tftic.java.bll.services;


import be.tftic.java.common.models.requests.update.ClotureEnqueteRequest;
import be.tftic.java.common.models.requests.create.PlainteCreateRequest;
import be.tftic.java.common.models.requests.filter.PlainteFilterRequest;
import be.tftic.java.common.models.responses.PlainteShortResponse;
import be.tftic.java.domain.entities.Plainte;

import java.util.List;

public interface PlainteService {

    List<PlainteShortResponse> findAll();

    Plainte findById(Long id);

    Plainte findByNumeroDossier(String numeroDossier);

    List<PlainteShortResponse> findByPlaignantId();

    List<PlainteShortResponse> findByPersonneConcernee();

    List<PlainteShortResponse> findByCriteria(PlainteFilterRequest f);

    Plainte create(PlainteCreateRequest form);

    void ouvrirEnquete(Long id);

    void cloturerEnquete(ClotureEnqueteRequest form);

    List<PlainteShortResponse> findByPlaignantIdWithCriteria(PlainteFilterRequest f);

}
