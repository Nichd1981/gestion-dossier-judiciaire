package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.PhoneService;
import be.tftic.java.dal.repositories.PhoneRepository;
import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.entities.Phone;
import be.tftic.java.domain.entities.User;
import be.tftic.java.domain.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Set;

/**
 * Classe de service pour la gestion des opérations liées à l'entité Téléphone.
 * Cette classe fournit une couche d'abstraction entre la couche de contrôleur et la couche de persistance,
 * permettant de gérer les opérations métier et de maintenir une séparation des préoccupations.
 *
 * @Service indique que cette classe est un composant Spring géré par le conteneur d'injection de dépendances.
 * Spring s'occupe de créer une instance unique de cette classe et de la fournir là où elle est nécessaire.
 * @RequiredArgsConstructor indique que le constructeur généré par Lombok ne prend en compte que les attributs finaux.
 * Dans ce cas, cela signifie que le constructeur injecte l'instance de TelephoneRepository fournie par Spring.
 * TelephoneService indique que cette classe implémente l'interface TelephoneService,
 * ce qui permet de garantir que les méthodes nécessaires sont fournies et facilite le remplacement ou l'extension de l'implémentation.
 */
@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    /**
     * Met à jour les informations d'un téléphone donné.
     * Si le téléphone n'existe pas, une exception RuntimeException est levée.
     *
     * @param id l'identifiant unique du téléphone à mettre à jour.
     * @param phone les nouvelles informations du téléphone, y compris le numéro et le libellé.
     * @return l'identifiant unique du téléphone mis à jour.
     * @throws RuntimeException si le téléphone n'existe pas.
     */
    @Override
    public Long update(Long id, Phone phone) throws AccessDeniedException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getRole() == Role.CITIZEN) {
            Person person = user.getPerson();
            Set<Phone> tels = person.getPhones();

            if (tels.stream().noneMatch(t -> t.getId().equals(id))) {
                throw new AccessDeniedException("Interdit : vous ne pouvez pas modifier ce numéro");
            }
        }

        Phone toUpdate = phoneRepository.findById(id).orElseThrow(
                //TODO : utiliser une exception custom quand on gerera les exceptions
                () -> new RuntimeException("Numéro de téléphone introuvable")
        );

        toUpdate.setNumber(phone.getNumber());
        toUpdate.setLabel(phone.getLabel());
        phoneRepository.save(toUpdate);

        return id;

    }
}