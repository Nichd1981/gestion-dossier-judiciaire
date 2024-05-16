package be.java.gestiondossierjudiciare.bll.services;

import be.java.gestiondossierjudiciare.domain.entities.Plainte;

import java.util.List;

public interface PlainteService {
    List<Plainte> findByPlaignantId(Long id);
}
