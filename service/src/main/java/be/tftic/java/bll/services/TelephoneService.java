package be.tftic.java.bll.services;


import be.tftic.java.domain.entities.Telephone;

import java.nio.file.AccessDeniedException;

public interface TelephoneService {

    Long update(Long id, Telephone telephone) throws AccessDeniedException;

}