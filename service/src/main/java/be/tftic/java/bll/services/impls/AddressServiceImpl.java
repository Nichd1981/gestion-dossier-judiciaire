package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.AddressService;
import be.tftic.java.dal.repositories.AddressRepository;
import be.tftic.java.domain.entities.Address;
import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.entities.User;
import be.tftic.java.domain.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.nio.file.AccessDeniedException;
import java.util.Set;

/**
 * Classe de service pour la gestion des opérations liées à l'entité Adresse.
 * Cette classe fournit une couche d'abstraction entre la couche de contrôleur et la couche de persistance,
 * permettant de gérer les opérations métier et de maintenir une séparation des préoccupations.
 *
 * @Service indique que cette classe est un composant Spring géré par le conteneur d'injection de dépendances.
 * Spring s'occupe de créer une instance unique de cette classe et de la fournir là où elle est nécessaire.
 * @RequiredArgsConstructor indique que le constructeur généré par Lombok ne prend en compte que les attributs finaux.
 * Dans ce cas, cela signifie que le constructeur injecte l'instance d'AdresseRepository fournie par Spring.
 * AdresseService indique que cette classe implémente l'interface AdresseService,
 * ce qui permet de garantir que les méthodes nécessaires sont fournies et facilite le remplacement ou l'extension de l'implémentation.
 */
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Long update(Long id, Address address) throws AccessDeniedException {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getRole() == Role.CITIZEN){
            Person person = user.getPerson();
            Set<Address> addresses = person.getAddress();
            if (addresses.stream().noneMatch(a -> a.getId().equals(id))) {
                // L'adresse que l'on veut modifier n'est pas une des adresses de ce citoyen
                throw new AccessDeniedException("Interdit : vous ne pouvez pas modifier cette adresse");
            }
        }

        /**
         * Met à jour l'adresse avec l'identifiant donné en utilisant les informations fournies.
         * Si l'adresse à mettre à jour n'est pas trouvée, une exception RuntimeException est levée.
         *
         * @param id l'identifiant unique de l'adresse à mettre à jour.
         * @param adresse l'objet Adresse contenant les nouvelles informations de l'adresse.
         * Les propriétés de cet objet sont utilisées pour remplacer les valeurs correspondantes de l'adresse existante.
         * @return l'identifiant de l'adresse mise à jour, qui est le même que l'identifiant fourni en entrée.
         * @throws RuntimeException si l'adresse à mettre à jour n'est pas trouvée dans la base de données.
         *
         */
        Address toUpdate = addressRepository.findById(id).orElseThrow(
                // TODO : utiliser une exception customisée pour gérer les cas d'échec de manière plus précise et adaptée à l'application.
                () -> new RuntimeException("Adresse not found")
        );

        // Met à jour les propriétés de l'adresse existante avec les nouvelles valeurs fournies.
        toUpdate.setStreet(address.getStreet());
        toUpdate.setNumber(address.getNumber());
        toUpdate.setCity(address.getCity());
        toUpdate.setPostcode(address.getPostcode());
        toUpdate.setCountry(address.getCountry());
        toUpdate.setLabel(address.getLabel());

        // Enregistre l'adresse mise à jour dans la base de données.
        addressRepository.save(toUpdate);

        // Retourne l'identifiant de l'adresse mise à jour.
        return id;

    }

}
