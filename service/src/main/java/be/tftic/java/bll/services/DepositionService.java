package be.tftic.java.bll.services;

import be.tftic.java.domain.entities.Deposition;

import java.util.List;

public interface DepositionService {
    List<Deposition> findAllDeposition(Long id);
}
