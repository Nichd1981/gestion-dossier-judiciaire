package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.TelephoneService;
import be.tftic.java.dal.repositories.TelephoneRepository;
import be.tftic.java.domain.entities.Telephone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelephoneServiceImpl implements TelephoneService {

    private final TelephoneRepository telephoneRepository;

    @Override
    public Long update(Long id, Telephone telephone) {

        Telephone toUpdate = telephoneRepository.findById(id).orElseThrow(
                //TODO : utiliser une exception custom quand on gerera les exceptions
                () -> new RuntimeException("Telephone not found")
        );

        toUpdate.setNumero(telephone.getNumero());
        toUpdate.setLibelle(telephone.getLibelle());

        telephoneRepository.save(toUpdate);

        return id;

    }
}
