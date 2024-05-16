package be.java.gestiondossierjudiciare.bll.services.impls;

import be.java.gestiondossierjudiciare.bll.services.PersonneService;
import be.java.gestiondossierjudiciare.dal.repositories.PersonneRepository;
import be.java.gestiondossierjudiciare.domain.entities.Personne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonneServiceImpl implements PersonneService {

    private final PersonneRepository personneRepository;

    @Override
    public Long update(Long id, Personne personne) {

        Personne toUpdate = personneRepository.findById(id).orElseThrow(
                //TODO : utiliser une exception custom quand on gerera les exceptions
                () -> new RuntimeException("Personne not found")
        );

        toUpdate.setNom(personne.getNom());
        toUpdate.setPrenom(personne.getPrenom());
        toUpdate.setGenre(personne.getGenre());
        toUpdate.setPhoto(personne.getPhoto());
        toUpdate.setEmpreinte(personne.getEmpreinte());
        toUpdate.setDateNaissance(personne.getDateNaissance());
        toUpdate.setLieuNaissance(personne.getLieuNaissance());

        personneRepository.save(toUpdate);

        return id;
    }

}
