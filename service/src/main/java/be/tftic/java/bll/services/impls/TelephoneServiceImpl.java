package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.TelephoneService;
import be.tftic.java.dal.repositories.TelephoneRepository;
import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Telephone;
import be.tftic.java.domain.entities.Utilisateur;
import be.tftic.java.domain.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TelephoneServiceImpl implements TelephoneService {

    private final TelephoneRepository telephoneRepository;

    @Override
    public Long update(Long id, Telephone telephone) throws AccessDeniedException {
        Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getRole() == Role.CITOYEN) {
            Personne personne = user.getPersonne();
            Set<Telephone> tels = personne.getTelephones();

            if (tels.stream().noneMatch(t -> t.getId().equals(id))) {
                throw new AccessDeniedException("Interdit : vous ne pouvez pas modifier ce numéro");
            }
        }

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
