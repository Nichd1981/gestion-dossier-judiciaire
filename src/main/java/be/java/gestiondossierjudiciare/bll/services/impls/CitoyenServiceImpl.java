package be.java.gestiondossierjudiciare.bll.services.impls;

import be.java.gestiondossierjudiciare.bll.services.CitoyenService;
import be.java.gestiondossierjudiciare.dal.repositories.CitoyenRepository;
import be.java.gestiondossierjudiciare.domain.entities.Citoyen;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CitoyenServiceImpl implements CitoyenService {

    private final CitoyenRepository citoyenRepository;

    @Override
    public Long update(Long id, Citoyen citoyen) {

        Citoyen toUpdate = citoyenRepository.findById(id).orElseThrow(
                //TODO : utiliser une exception custom quand on gerera les exceptions
                () -> new RuntimeException("Citoyen not found")
        );

        toUpdate.setNom(citoyen.getNom());
        toUpdate.setPrenom(citoyen.getPrenom());
        toUpdate.setGenre(citoyen.getGenre());
        toUpdate.setPhoto(citoyen.getPhoto());
        toUpdate.setEmpreinte(citoyen.getEmpreinte());
        toUpdate.setDateNaissance(citoyen.getDateNaissance());
        toUpdate.setLieuNaissance(citoyen.getLieuNaissance());

        citoyenRepository.save(toUpdate);

        return id;
    }

}
