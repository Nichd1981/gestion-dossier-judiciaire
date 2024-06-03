package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.exceptions.EntityNotFoundException;
import be.tftic.java.bll.services.DepositionService;
import be.tftic.java.bll.specifications.DepositionSpecification;
import be.tftic.java.common.models.requests.filter.DepositionFilterRequest;
import be.tftic.java.common.models.responses.DepositionShortResponse;
import be.tftic.java.dal.repositories.DepositionRepository;
import be.tftic.java.dal.repositories.ComplaintRepository;
import be.tftic.java.domain.entities.Deposition;
import be.tftic.java.domain.entities.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Classe de service pour la gestion des opérations liées à l'entité Déposition.
 * Cette classe fournit une couche d'abstraction entre la couche de contrôleur et la couche de persistance,
 * permettant de gérer les opérations métier et de maintenir une séparation des préoccupations.
 *
 * @Service indique que cette classe est un composant Spring géré par le conteneur d'injection de dépendances.
 * Spring s'occupe de créer une instance unique de cette classe et de la fournir là où elle est nécessaire.
 * @RequiredArgsConstructor indique que le constructeur généré par Lombok ne prend en compte que les attributs finaux.
 * Dans ce cas, cela signifie que le constructeur injecte les instances de PlainteRepository et DepositionRepository fournies par Spring.
 * DepositionService indique que cette classe implémente l'interface DepositionService,
 * ce qui permet de garantir que les méthodes nécessaires sont fournies et facilite le remplacement ou l'extension de l'implémentation.
 */
@Service
@RequiredArgsConstructor
public class DepositionServiceImpl implements DepositionService {

    private final ComplaintRepository complaintRepository;
    private final DepositionRepository depositionRepository;

    /**
     * Récupère toutes les dépositions associées à une plainte donnée.
     * Si la plainte n'existe pas, une exception RuntimeException est levée.
     *
     * @param id l'identifiant unique de la plainte pour laquelle récupérer les dépositions.
     * @return la liste des dépositions associées à la plainte, ou une liste vide si aucune déposition n'est associée.
     * @throws RuntimeException si la plainte n'existe pas dans la base de données.
     */
    @Override
    public List<DepositionShortResponse> findAllDeposition(Long id) {
        Complaint plainte = getComplaint(id);

        return depositionRepository.findByComplaint(plainte)
                .stream()
                .map(DepositionShortResponse::fromEntity)
                .toList();
    }

    /**
     * Récupère les dépositions qui correspondent aux critères de recherche donnés.
     * Les critères de recherche incluent une borne inférieure de date, une borne supérieure de date et un mot-clé.
     * Les dépositions sont filtrées en fonction des critères fournis, et seules les dépositions correspondantes sont renvoyées sous forme de liste de DepositionShortResponse.
     *
     * @param f le filtre de recherche contenant les critères de recherche pour les dépositions.
     * @return la liste des dépositions qui correspondent aux critères de recherche donnés sous forme de DepositionShortResponse, ou une liste vide si aucune déposition ne correspond.
     */
    @Override
    public List<DepositionShortResponse> findByCriteria(DepositionFilterRequest f) {
        return depositionRepository.findAll(getSpecification(f))
                .stream()
                .map(DepositionShortResponse::fromEntity)
                .toList();
    }

    private Complaint getComplaint(Long complaintId){
        return complaintRepository.findById(complaintId).orElseThrow(
                () -> new EntityNotFoundException("Complaint not found")
        );
    }

    /**
     * Construit une spécification pour le filtrage des dépositions en fonction des critères de recherche donnés.
     * Les critères de recherche incluent une borne inférieure de date, une borne supérieure de date et un mot-clé.
     * La spécification est construite en combinant les différents critères à l'aide de l'opérateur logique AND.
     *
     * @param f le filtre de recherche contenant les critères de recherche pour les dépositions.
     * @return la spécification pour le filtrage des dépositions en fonction des critères de recherche donnés.
     */
    private Specification<Deposition> getSpecification(DepositionFilterRequest f) {
        Specification<Deposition> spec = Specification.where(null);

        if (f.getDateLowerBound() != null) {
            spec = spec.and(DepositionSpecification.getByDateLowerBound(f.getDateLowerBound()));
        }

        if (f.getDateUpperBound() != null) {
            spec = spec.and(DepositionSpecification.getByDateUpperBound(f.getDateUpperBound()));
        }

        if (f.getKeyword() != null) {
            spec = spec.and(DepositionSpecification.getByKeyword(f.getKeyword()));
        }

        return spec;
    }
}
