package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.PersonneService;
import be.tftic.java.dal.repositories.PersonneRepository;
import be.tftic.java.domain.entities.Personne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonneServiceImpl implements PersonneService {

    private final PersonneRepository personneRepository;

    @Override
    public Personne findById(Long id) {
        return getPersonne(id);
    }

    @Override
    public Personne findByNationalRegister(String nationalNumber) {
        return getPersonne(nationalNumber);
    }

    @Override
    public Long update(Long id, Personne personne) {

        Personne toUpdate = getPersonne(id);

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

    private Personne getPersonne(Long id){
        return personneRepository.findById(id).orElseThrow(
                //TODO : utiliser une exception custom quand on gerera les exceptions
                () -> new RuntimeException("Personne not found")
        );
    }

    private Personne getPersonne(String nationalRegisterNumber){
        return personneRepository.findByRegistreNational(nationalRegisterNumber).orElseThrow(
                //TODO : utiliser une exception custom quand on gerera les exceptions
                () -> new RuntimeException("Personne not found")
        );
    }

}
