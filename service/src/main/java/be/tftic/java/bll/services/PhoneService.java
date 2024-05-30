package be.tftic.java.bll.services;


import be.tftic.java.domain.entities.Phone;
import java.nio.file.AccessDeniedException;

public interface PhoneService {

    Long update(Long id, Phone phone) throws AccessDeniedException;

}