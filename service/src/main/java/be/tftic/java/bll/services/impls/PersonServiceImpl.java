package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.PersonService;
import be.tftic.java.dal.repositories.PersonRepository;
import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Classe de service pour la gestion des opérations liées à l'entité Personne.
 * Cette classe fournit une couche d'abstraction entre la couche de contrôleur et la couche de persistance,
 * permettant de gérer les opérations métier et de maintenir une séparation des préoccupations.
 *
 * @Service indique que cette classe est un composant Spring géré par le conteneur d'injection de dépendances.
 * Spring s'occupe de créer une instance unique de cette classe et de la fournir là où elle est nécessaire.
 * @RequiredArgsConstructor indique que le constructeur généré par Lombok ne prend en compte que les attributs finaux.
 * Dans ce cas, cela signifie que le constructeur injecte l'instance de PersonneRepository fournie par Spring.
 * PersonneService indique que cette classe implémente l'interface PersonneService,
 * ce qui permet de garantir que les méthodes nécessaires sont fournies et facilite le remplacement ou l'extension de l'implémentation.
 */

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    /**
     * Récupère une personne donnée à partir de son identifiant unique.
     * Si la personne n'existe pas, une exception RuntimeException est levée.
     *
     * @param id l'identifiant unique de la personne à récupérer.
     * @return la personne correspondant à l'identifiant unique donné.
     * @throws RuntimeException si la personne n'existe pas.
     */
    @Override
    public Person findById(Long id) {
       return getPerson(id);
    }

    @Override
    public Person findByNationalRegister(String nationalNumber) {
        return getPerson(nationalNumber);
    }

    /**
     * Met à jour les informations d'une personne donnée.
     * Si la personne n'existe pas, une exception RuntimeException est levée.
     *
     * @param id l'identifiant unique de la personne à mettre à jour.
     * @param person les nouvelles informations de la personne, y compris le nom, le prénom, le genre, la photo, l'empreinte, la date de naissance et le lieu de naissance.
     * @return l'identifiant unique de la personne mise à jour.
     * @throws RuntimeException si la personne n'existe pas.
     */
    @Override
    public Long update(Long id, Person person) {

        if (id == null) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            id = user.getPerson().getId();
        }

        Person toUpdate = getPerson(id);

        toUpdate.setName(person.getName());
        toUpdate.setFirstname(person.getFirstname());
        toUpdate.setGender(person.getGender());
        toUpdate.setPicture(person.getPicture());
        toUpdate.setImprint(person.getImprint());
        toUpdate.setBirthdate(person.getBirthdate());
        toUpdate.setBirthplace(person.getBirthplace());

        personRepository.save(toUpdate);

        return id;
    }

    private Person getPerson(Long id){
        return personRepository.findById(id).orElseThrow(
                //TODO : utiliser une exception custom quand on gerera les exceptions
                () -> new RuntimeException("Personne introuvable")
        );
    }

    private Person getPerson(String nationalRegister){
        return personRepository.findByNationalRegister(nationalRegister).orElseThrow(
                //TODO : utiliser une exception custom quand on gerera les exceptions
                () -> new RuntimeException("Personne introuvable")
        );
    }

}
