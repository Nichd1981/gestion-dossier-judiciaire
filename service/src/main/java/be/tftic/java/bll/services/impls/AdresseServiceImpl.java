package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.AdresseService;
import be.tftic.java.dal.repositories.AdresseRepository;
import be.tftic.java.domain.entities.Adresse;
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
