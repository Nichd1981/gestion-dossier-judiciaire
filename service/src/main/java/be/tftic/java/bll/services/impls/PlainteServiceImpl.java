package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.JugementService;
import be.tftic.java.bll.services.PersonneService;
import be.tftic.java.bll.services.PlainteService;
import be.tftic.java.bll.specifications.PlainteSpecification;
import be.tftic.java.common.models.requests.ClotureEnqueteRequest;
import be.tftic.java.common.models.requests.PlainteCreateRequest;
import be.tftic.java.dal.repositories.PlainteRepository;
import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.enums.Statut;
import be.tftic.java.domain.enums.TypePlainte;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe de service pour la gestion des opérations liées à l'entité Plainte.
 * Cette classe fournit une couche d'abstraction entre la couche de contrôleur et la couche de persistance,
 * permettant de gérer les opérations métier et de maintenir une séparation des préoccupations.
 *
 * @Service indique que cette classe est un composant Spring géré par le conteneur d'injection de dépendances.
 * Spring s'occupe de créer une instance unique de cette classe et de la fournir là où elle est nécessaire.
 * @RequiredArgsConstructor indique que le constructeur généré par Lombok ne prend en compte que les attributs finaux.
 * Dans ce cas, cela signifie que le constructeur injecte les instances de PlainteRepository, PersonneService et JugementService fournies par Spring.
 * PlainteService indique que cette classe implémente l'interface PlainteService,
 * ce qui permet de garantir que les méthodes nécessaires sont fournies et facilite le remplacement ou l'extension de l'implémentation.
 */
@Service
@RequiredArgsConstructor
public class PlainteServiceImpl implements PlainteService {

    private final PlainteRepository plainteRepository;
    private final PersonneService personneService;
    private final JugementService jugementService;

    /**
     * Récupère toutes les plaintes déposées par une personne donnée.
     *
     * @param id l'identifiant unique de la personne pour laquelle récupérer les plaintes.
     * @return la liste des plaintes déposées par la personne, ou une liste vide si aucune plainte n'a été déposée.
     */
    @Override
    public List<Plainte> findByPlaignantId(Long id) {
        return plainteRepository.findByPlaignantId(id);
    }

    /**
     * Récupère toutes les plaintes dans lesquelles une personne donnée est impliquée.
     *
     * @param personne la personne pour laquelle récupérer les plaintes.
     * @return la liste des plaintes dans lesquelles la personne est impliquée, ou une liste vide si aucune plainte ne concerne la personne.
     */
    @Override
    public List<Plainte> findByPersonneConcernee(Personne personne) {
        return plainteRepository.findByPersonnesConcernees(personne);
    }

    /**
     * Récupère toutes les plaintes enregistrées dans la base de données.
     *
     * @return la liste de toutes les plaintes, ou une liste vide si aucune plainte n'est enregistrée.
     */
    @Override
    public List<Plainte> findAll() {
        return plainteRepository.findAll();
    }

    /**
     * Récupère une plainte donnée à partir de son identifiant unique.
     * Si la plainte n'existe pas, une exception RuntimeException est levée.
     *
     * @param id l'identifiant unique de la plainte à récupérer.
     * @return la plainte correspondant à l'identifiant unique donné.
     * @throws RuntimeException si la plainte n'existe pas.
     */
    @Override
    public Plainte findById(Long id) {
        return plainteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Le plainte n'existe pas")
        );
    }

    /**
     * Récupère une plainte donnée à partir de son numéro de dossier.
     * Si la plainte n'existe pas, une exception RuntimeException est levée.
     *
     * @param numeroDossier le numéro de dossier de la plainte à récupérer.
     * @return la plainte correspondant au numéro de dossier donné.
     * @throws RuntimeException si la plainte n'existe pas.
     */
    @Override
    public Plainte findByNumeroDossier(String numeroDossier) {
        return plainteRepository.findByNumeroDossier(numeroDossier).orElseThrow(
                () -> new RuntimeException("Le plainte n'existe pas")
        );
    }

    /**
     * Récupère les plaintes qui correspondent aux critères de recherche donnés.
     * Les critères de recherche incluent un numéro de dossier, une borne inférieure de date, une borne supérieure de date et un statut.
     * Les plaintes sont filtrées en fonction des critères fournis, et seules les plaintes correspondantes sont renvoyées.
     *
     * @param numeroDossier le numéro de dossier de la plainte à récupérer, ou null si ce critère est ignoré.
     * @param lowerBound la borne inférieure de date à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @param upperBound la borne supérieure de date à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @param statut le statut à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @return la liste des plaintes qui correspondent aux critères de recherche donnés, ou une liste vide si aucune plainte ne correspond.
     */
    @Override
    public List<Plainte> findByCriteria(String numeroDossier, LocalDate lowerBound, LocalDate upperBound, String statut) {
        Specification<Plainte> spec = getSpecification(numeroDossier, lowerBound, upperBound, statut, null, null);
        return plainteRepository.findAll(spec);
    }

    /**
     * Crée une nouvelle plainte à partir des informations fournies dans le formulaire.
     *
     * @param form le formulaire contenant les informations de la plainte, y compris l'identifiant unique du plaignant, l'identifiant unique de l'agent traitant et les identifiants uniques des personnes concernées.
     * @return la plainte nouvellement créée.
     */
    @Override
    public Plainte create(PlainteCreateRequest form) {

        Plainte plainte = form.toEntity();
        plainte.setPlaignant(personneService.findById(form.idPlaignant()));
        plainte.setAgentTraitant(personneService.findById(form.idAgentTraitant()));
        form.idConcernes().forEach(id -> {
            Personne personne = personneService.findById(id);
            plainte.getPersonnesConcernees().add(personne);
        });

        return plainteRepository.save(plainte);
    }

    /**
     * Met à jour le statut d'une plainte donnée à "En cours".
     *
     * @param id l'identifiant unique de la plainte à mettre à jour.
     */
    @Override
    public void ouvrirEnquete(Long id) {
        Plainte toUpdate = this.findById(id);

        toUpdate.setStatut(Statut.EN_COURS);
        plainteRepository.save(toUpdate);
    }

    /**
     * Met à jour le statut d'une plainte donnée à "Clôturée" et crée un jugement associé si le type de plainte est Délit ou Crime.
     * Si la plainte n'est pas déjà "En cours", une exception RuntimeException est levée.
     *
     * @param form le formulaire contenant les informations de clôture de la plainte, y compris l'identifiant unique de la plainte et le type de plainte.
     * @throws RuntimeException si la plainte n'est pas déjà "En cours".
     */
    @Override
    public void cloturerEnquete(ClotureEnqueteRequest form) {
        Plainte toUpdate = this.findById(form.plainteId());

        // Si la plainte n'est pas encore ouverte, exception!
        if (toUpdate.getStatut() != Statut.EN_COURS) {
            //TODO : custom exception
            throw new RuntimeException("La plainte doit être \"En cours\" pour pouvoir être clôturée.");
        }

        toUpdate.setStatut(Statut.CLOTUREE);
        toUpdate.setTypePlainte(TypePlainte.valueOf(form.type()));
        // Si le type de plainte est Délit ou Crime, créer un Jugement associé
        if (TypePlainte.valueOf(form.type()) == TypePlainte.DELIT || TypePlainte.valueOf(form.type()) == TypePlainte.CRIME) {
            jugementService.create(form.plainteId());
        }
        plainteRepository.save(toUpdate);
    }

    /**
     * Récupère les plaintes déposées par une personne donnée qui correspondent aux critères de recherche donnés.
     * Les critères de recherche incluent un numéro de dossier, une borne inférieure de date, une borne supérieure de date, un statut et un type de plainte.
     * Les plaintes sont filtrées en fonction des critères fournis et de la personne qui les a déposées, et seules les plaintes correspondantes sont renvoyées.
     *
     * @param plaignant la personne pour laquelle récupérer les plaintes, ou null si ce critère est ignoré.
     * @param type le type de plainte à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @param upperBound la borne supérieure de date à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @param lowerBound la borne inférieure de date à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @param numeroDossier le numéro de dossier de la plainte à récupérer, ou null si ce critère est ignoré.
     * @param statut le statut à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @return la liste des plaintes qui correspondent aux critères de recherche donnés et qui ont été déposées par la personne donnée, ou une liste vide si aucune plainte ne correspond.
     */
    @Override
    public List<Plainte> findByPlaignantIdWithCriteria(Personne plaignant, String type, LocalDate upperBound, LocalDate lowerBound, String numeroDossier, String statut) {
        Specification <Plainte> spec = getSpecification(numeroDossier, lowerBound, upperBound, statut, type, plaignant);
        return plainteRepository.findAll(spec);
    }

    /**
     * Construit une spécification pour le filtrage des plaintes en fonction des critères de recherche donnés.
     * Les critères de recherche incluent un numéro de dossier, une borne inférieure de date, une borne supérieure de date, un statut, un type de plainte et une personne.
     * La spécification est construite en combinant les différents critères à l'aide de l'opérateur logique AND.
     *
     * @param numeroDossier le numéro de dossier de la plainte à récupérer, ou null si ce critère est ignoré.
     * @param lowerBound la borne inférieure de date à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @param upperBound la borne supérieure de date à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @param statut le statut à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @param type le type de plainte à utiliser pour le filtrage des plaintes, ou null si ce critère est ignoré.
     * @param plaignant la personne pour laquelle récupérer les plaintes, ou null si ce critère est ignoré.
     * @return la spécification pour le filtrage des plaintes en fonction des critères de recherche donnés.
     */
    private Specification<Plainte> getSpecification(String numeroDossier, LocalDate lowerBound, LocalDate upperBound, String statut, String type, Personne plaignant) {

        Specification<Plainte> spec = Specification.where(null);
        if(!numeroDossier.isBlank()){
            spec = spec.and(PlainteSpecification.getByNumeroDossier(numeroDossier));
        }
        if(lowerBound != null){
            spec = spec.and(PlainteSpecification.getByDateLowerBound(lowerBound));
        }
        if(upperBound != null){
            spec = spec.and(PlainteSpecification.getByDateUpperBound(upperBound));
        }
        if(statut != null && !statut.isBlank()){
            spec = spec.and(PlainteSpecification.getByStatut(Statut.valueOf(statut)));
        }
        if(type != null && !type.isBlank()){
            spec = spec.and(PlainteSpecification.getByType(TypePlainte.valueOf(type)));
        }
        if(plaignant != null){
            spec = spec.and(PlainteSpecification.getByPlaignant(plaignant));
        }
        return spec;
    }

}
