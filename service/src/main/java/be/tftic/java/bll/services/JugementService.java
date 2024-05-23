package be.tftic.java.bll.services;


import be.tftic.java.common.models.requests.JugementUpdateRequest;
import be.tftic.java.domain.entities.Jugement;

import java.time.LocalDate;
import java.util.List;

public interface JugementService {

    void create(Long plainteId);

    List<Jugement> findAllForPlainte(Long plainteId);

    List<Jugement> findWithCriteria(Long plainteId, String numeroDossier, LocalDate lowerBound, LocalDate upperBound, String keyWord, String decision);

    void cloturerJugement(JugementUpdateRequest jugement);

}
