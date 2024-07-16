package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.exceptions.entity.EntityNotFoundException;
import be.tftic.java.bll.services.PersonService;
import be.tftic.java.common.models.requests.create.PersonCreateRequest;
import be.tftic.java.common.models.responses.PagedResponse;
import be.tftic.java.common.models.responses.PersonDetailResponse;
import be.tftic.java.common.models.responses.PersonShortResponse;
import be.tftic.java.dal.repositories.PersonRepository;
import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Join;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

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

    @Override
    public Person create(PersonCreateRequest request) {
        Person p = request.toEntity();
        return personRepository.save(p);
    }

    @Override
    public PagedResponse<PersonShortResponse> getAll(Map<String, String> params, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Person> pagedPersons = personRepository
                .findAll(filterByParams(params), pageable);

        return new PagedResponse<>(
                pagedPersons.getContent()
                        .stream()
                        .map(PersonShortResponse::fromEntity)
                        .toList(),
                pageable.getPageSize(),
                pagedPersons.getTotalElements(),
                pagedPersons.getTotalPages()
        );
    }

//    public PagedResponse<PersonShortResponse> getCustomersForLawyer(Long lawyerId, Map<String, String> params, int page, int pageSize) {
//
//        Pageable pageable = PageRequest.of(page, pageSize);
//
//        Page<Person> pagedPersons = personRepository
//                .findAll(filterByParams(params), pageable);
//
//        return new PagedResponse<>(
//                pagedPersons.getContent()
//                        .stream()
//                        .map(PersonShortResponse::fromEntity)
//                        .toList(),
//                pageable.getPageSize(),
//                pagedPersons.getTotalElements(),
//                pagedPersons.getTotalPages()
//        );
//
////        return personRepository.findCustomersByLawyer(lawyerId)
////                .stream()
////                .map(PersonShortResponse::fromEntity)
////                .toList();
//    }

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
    public PersonDetailResponse findDetailsById(Long id) {
        return PersonDetailResponse.fromEntity(findById(id));
    }

    @Override
    public PersonDetailResponse findUserDetails() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return PersonDetailResponse.fromEntity(findById(user.getPerson().getId()));
    }

    @Override
    public List<PersonShortResponse> getAllDetailsPerson() {
        return personRepository.findAll()
                .stream()
                .map(PersonShortResponse::fromEntity)
                .toList();
    }

    @Override
    public Person findByNationalRegister(String nationalNumber) {
        return getPerson(nationalNumber);
    }

    @Override
    public boolean existsByNationalRegister(String nationalRegister) {
        return personRepository.existsByNationalRegister(nationalRegister);
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
                () -> new EntityNotFoundException("Person not found")
        );
    }

    private Person getPerson(String nationalRegister){
        return personRepository.findByNationalRegister(nationalRegister).orElseThrow(
                () -> new EntityNotFoundException("Person not found")
        );
    }

    private Specification<Person> filterByParams(Map<String, String> params) {
        Specification<Person> specification = Specification.where(null);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!entry.getValue().isBlank()) {
                specification = specification.and(filterBy(entry.getKey(), entry.getValue()));
            }
        }

        return specification;
    }

    private Specification<Person> filterBy(String key, String value) {
        return (root, query, criteriaBuilder) ->
                switch (key) {
                    case "birthDateLowerBound" ->
                            criteriaBuilder.greaterThanOrEqualTo(root.get("birthdate"), LocalDate.parse(value, DateTimeFormatter.ISO_DATE_TIME));

                    case "birthDateUpperBound" ->
                            criteriaBuilder.lessThanOrEqualTo(root.get("birthdate"), LocalDate.parse(value, DateTimeFormatter.ISO_DATE_TIME));

                    case "name" ->
                            criteriaBuilder.like(root.get("name"), "%" + value + "%");

                    case "firstname" ->
                            criteriaBuilder.like(root.get("firstname"), "%" + value + "%");

                    case "nationalRegister" ->
                            criteriaBuilder.like(root.get("nationalRegister"), "%" + value + "%");

                    case "birthPlace" ->
                            criteriaBuilder.like(root.get("birthPlace"), "%" + value + "%");

                    case "gender" ->
                            criteriaBuilder.equal(root.get("gender"), value);

                    case "lawyerId" ->
                    {
                        Join<Person, Person> lawyerJoin = root.join("lawyer");
                        yield criteriaBuilder.equal(lawyerJoin.get("id"), value);
                    }

                    default -> null;
                };
    }

}
