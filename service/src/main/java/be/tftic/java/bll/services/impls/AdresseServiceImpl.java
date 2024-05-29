package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.AdresseService;
import be.tftic.java.dal.repositories.AdresseRepository;
import be.tftic.java.domain.entities.Adresse;
import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Utilisateur;
import be.tftic.java.domain.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdresseServiceImpl implements AdresseService {

    private final AdresseRepository adresseRepository;

    @Override
    public Long update(Long id, Adresse adresse) throws AccessDeniedException {

        Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getRole() == Role.CITOYEN){
            Personne personne = user.getPersonne();
            Set<Adresse> adresses = personne.getAdresses();
            if (adresses.stream().noneMatch(a -> a.getId().equals(id))) {
                // L'adresse que l'on veut modifier n'est pas une des adresses de ce citoyen
                throw new AccessDeniedException("Interdit : vous ne pouvez pas modifier cette adresse");
            }
        }

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
