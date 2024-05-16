package be.java.gestiondossierjudiciare.bll.services.impls;

import be.java.gestiondossierjudiciare.bll.services.AdresseService;
import be.java.gestiondossierjudiciare.dal.repositories.AdresseRepository;
import be.java.gestiondossierjudiciare.domain.entities.Adresse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdresseServiceImpl implements AdresseService {

    private final AdresseRepository adresseRepository;

    @Override
    public Long update(Long id, Adresse adresse) {

        Adresse toUpdate = adresseRepository.findById(id).orElseThrow(
                //TODO : utiliser une exception custom quand on gerera les exceptions
                () -> new RuntimeException("Telephone not found")
        );

        toUpdate.setRue(adresse.getRue());
        toUpdate.setNumero(adresse.getNumero());
        toUpdate.setVille(adresse.getVille());
        toUpdate.setCodePostal(adresse.getCodePostal());
        toUpdate.setPays(adresse.getPays());
        toUpdate.setLibelle(adresse.getLibelle());

        adresseRepository.save(toUpdate);

        return id;

    }

}
