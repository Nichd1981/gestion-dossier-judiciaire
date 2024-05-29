package be.tftic.java.bll.services;


import be.tftic.java.domain.entities.Adresse;

import java.nio.file.AccessDeniedException;

public interface AdresseService {

    Long update(Long id, Adresse adresse) throws AccessDeniedException;


}
