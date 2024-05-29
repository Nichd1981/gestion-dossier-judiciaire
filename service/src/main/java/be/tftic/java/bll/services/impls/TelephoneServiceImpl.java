package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.TelephoneService;
import be.tftic.java.dal.repositories.TelephoneRepository;
import be.tftic.java.domain.entities.Telephone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
public class TelephoneServiceImpl implements TelephoneService {

    private final TelephoneRepository telephoneRepository;

    /**
     * Met à jour les informations d'un téléphone donné.
     * Si le téléphone n'existe pas, une exception RuntimeException est levée.
     *
     * @param id l'identifiant unique du téléphone à mettre à jour.
     * @param telephone les nouvelles informations du téléphone, y compris le numéro et le libellé.
     * @return l'identifiant unique du téléphone mis à jour.
     * @throws RuntimeException si le téléphone n'existe pas.
     */
    @Override
    public Long update(Long id, Telephone telephone) {

        Telephone toUpdate = telephoneRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Telephone not found")
        );

        toUpdate.setNumero(telephone.getNumero());
        toUpdate.setLibelle(telephone.getLibelle());

        telephoneRepository.save(toUpdate);

        return id;

    }

}
