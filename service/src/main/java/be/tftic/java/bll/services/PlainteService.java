package be.tftic.java.bll.services;


import be.tftic.java.common.models.requests.ClotureEnqueteRequest;
import be.tftic.java.common.models.requests.PlainteCreateRequest;
import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Plainte;

import java.time.LocalDate;
import java.util.List;

public interface PlainteService {

    List<Plainte> findAll();

    Plainte findById(Long id);

    Plainte findByNumeroDossier(String numeroDossier);

    List<Plainte> findByPlaignantId(Long id);

    List<Plainte> findByPersonneConcernee(Personne personne);

    List<Plainte> findByCriteria(String numeroDossier, LocalDate lowerBound, LocalDate upperBound, String statut);

    Plainte create(PlainteCreateRequest form);

    void ouvrirEnquete(Long id);

    void cloturerEnquete(ClotureEnqueteRequest form);

    List<Plainte> findByPlaignantIdWithCriteria(Personne plaignant, String type, LocalDate upperBound, LocalDate lowerBound, String numeroDossier, String statut);

}
