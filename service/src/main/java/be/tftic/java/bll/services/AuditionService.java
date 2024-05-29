package be.tftic.java.bll.services;

import be.tftic.java.domain.entities.Audition;
import be.tftic.java.domain.entities.Personne;

import java.time.LocalDate;
import java.util.List;


public interface AuditionService {

	List<Audition> findAllAudition(Long id);

	List<Audition> findAll();

	List<Audition> findAuditionByCriteria(Personne personne, LocalDate lowerBound, LocalDate upperBound, String motCle);


}
