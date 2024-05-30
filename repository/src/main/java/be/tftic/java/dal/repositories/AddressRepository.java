package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface pour la gestion des opérations de persistance liées à l'entité Adresse.
 *
 * La persistance des données fait référence au stockage et à la récupération des données d'une application,
 * généralement dans une base de données, de manière à ce qu'elles survivent à la fin de l'exécution de l'application
 * ou à des événements tels que des redémarrages ou des pannes.
 *
 * @Repository indique que cette interface est un composant Spring gérant les opérations de persistance.
 * JpaRepository<Adresse, Long> indique que cette interface étend JpaRepository et qu'elle est utilisée pour gérer
 * les opérations de persistance de l'entité Adresse avec une clé primaire de type Long.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
