package be.java.gestiondossierjudiciare.bll.services;

import be.java.gestiondossierjudiciare.domain.entities.Personne;

public interface PersonneService {

    Personne findById(Long id);

    Long update(Long id, Personne personne);

}
