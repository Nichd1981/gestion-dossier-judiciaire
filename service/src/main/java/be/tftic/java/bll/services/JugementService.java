package be.tftic.java.bll.services;


import be.tftic.java.domain.entities.Jugement;

import java.time.LocalDate;
import java.util.List;

public interface JugementService {

    void create(Long plainteId);

    List<Jugement> findAll(Long plainteId);

    List<Jugement> findWithCriteria(Long plainteId, LocalDate lowerBound, LocalDate upperBound, String keyWord);

    void cloturerJugement(String decision, String commentaire);


}
