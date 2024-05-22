package be.tftic.java.bll.services;


import be.tftic.java.domain.entities.Telephone;

public interface TelephoneService {

    Long update(Long id, Telephone telephone);

}