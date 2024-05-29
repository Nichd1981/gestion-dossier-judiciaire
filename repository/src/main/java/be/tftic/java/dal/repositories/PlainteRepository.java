package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Plainte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

/**
 * Dépôt pour les opérations de persistance des entités Plainte.
 *
 * Fournit des méthodes pour les opérations CRUD de base ainsi que des capacités de requête spécifiée.
 * Hérite de JpaRepository pour les opérations CRUD et de JpaSpecificationExecutor
 * pour les requêtes basées sur des spécifications.
 */
@Repository
public interface PlainteRepository extends JpaRepository<Plainte, Long>, JpaSpecificationExecutor<Plainte> {

    /**
     * Récupère une liste de plaintes associées à l'identifiant d'un plaignant.
     *
     * @param id l'identifiant du plaignant
     * @return une liste d'entités Plainte associées au plaignant spécifié
     */
    @Query("select p from Plainte p where p.plaignant.id = :id")
    List<Plainte> findByPlaignantId(Long id);

    /**
     * Récupère une plainte par son numéro de dossier.
     *
     * @param numeroDossier le numéro de dossier de la plainte
     * @return un Optional contenant l'entité Plainte correspondant au numéro de dossier, ou vide si aucune plainte n'est trouvée
     */
    @Query("""
            SELECT p FROM Plainte p WHERE p.numeroDossier ilike :numeroDossier
            """)
    Optional<Plainte> findByNumeroDossier(String numeroDossier);

    /**
     * Récupère une liste de plaintes associées à une personne concernée.
     *
     * @param personne l'entité Personne concernée
     * @return une liste d'entités Plainte associées à la personne spécifiée
     */
    @Query("SELECT p FROM Plainte p WHERE :personne MEMBER OF p.personnesConcernees")
    List<Plainte> findByPersonnesConcernees(Personne personne);

}

