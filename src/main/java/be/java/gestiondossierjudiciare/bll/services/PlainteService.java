package be.java.gestiondossierjudiciare.bll.services;

import be.java.gestiondossierjudiciare.domain.entities.Plainte;
import be.java.gestiondossierjudiciare.domain.enums.Statut;

import java.time.LocalDate;
import java.util.List;

public interface PlainteService {

    List<Plainte> findAll();

    Plainte findById(Long id);

    Plainte findByNumeroDossier(String numeroDossier);

    List<Plainte> findByCriteria(String numeroDossier, LocalDate lowerBound, LocalDate upperBound, String statut);


}
