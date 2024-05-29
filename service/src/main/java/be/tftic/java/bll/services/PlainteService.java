package be.tftic.java.bll.services;


import be.tftic.java.common.models.requests.ClotureEnqueteRequest;
import be.tftic.java.common.models.requests.PlainteCreateRequest;
import be.tftic.java.common.models.requests.PlainteFilterRequest;
import be.tftic.java.common.models.responses.PlainteShortResponse;
import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Plainte;

import java.time.LocalDate;
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
