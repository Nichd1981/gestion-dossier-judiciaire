package be.java.gestiondossierjudiciare.bll.services;

import be.java.gestiondossierjudiciare.api.forms.PlainteCreateForm;
import be.java.gestiondossierjudiciare.domain.entities.Personne;
import be.java.gestiondossierjudiciare.domain.entities.Plainte;

import java.time.LocalDate;
import java.util.List;

public interface PlainteService {

    List<Plainte> findAll();

    Plainte findById(Long id);

    Plainte findByNumeroDossier(String numeroDossier);

    List<Plainte> findByPlaignantId(Long id);

    List<Plainte> findByPersonneConcernee(Personne personne);

    List<Plainte> findByCriteria(String numeroDossier, LocalDate lowerBound, LocalDate upperBound, String statut);

    Plainte create(PlainteCreateForm form);
}
