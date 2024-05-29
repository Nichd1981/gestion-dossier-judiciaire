package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.PersonneService;
import be.tftic.java.dal.repositories.PersonneRepository;
import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonneServiceImpl implements PersonneService {

    private final PersonneRepository personneRepository;

    @Override
    public Personne findById(Long id) {
        return personneRepository.findById(id).orElseThrow(
                //TODO : exception custom
                () -> new RuntimeException("Personne non trouvée")
        );
    }

    @Override
    public Long update(Long id, Personne personne) {

        if (id == null) {
            Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            id = user.getPersonne().getId();
        }

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
