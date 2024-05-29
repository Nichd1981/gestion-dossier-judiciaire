package be.tftic.java.bll.services;


import be.tftic.java.common.models.requests.update.JugementUpdateRequest;
import be.tftic.java.common.models.responses.JugementResponse;

import java.time.LocalDate;
import java.util.List;

public interface JugementService {

    void create(Long plainteId);

    List<JugementResponse> findAllForPlainte(Long plainteId);

    List<JugementResponse> findWithCriteria(Long plainteId, String numeroDossier, LocalDate lowerBound, LocalDate upperBound, String keyWord, String decision);

    void cloturerJugement(JugementUpdateRequest jugement);

}
