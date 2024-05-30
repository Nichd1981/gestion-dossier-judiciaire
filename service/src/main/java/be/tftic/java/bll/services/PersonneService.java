package be.tftic.java.bll.services;


import be.tftic.java.domain.entities.Personne;

public interface PersonneService {

    Personne findById(Long id);

    Personne findByNationalRegister(String nationalRegister);

    Long update(Long id, Personne personne);

}
