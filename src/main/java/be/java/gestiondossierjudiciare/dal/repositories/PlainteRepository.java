package be.java.gestiondossierjudiciare.dal.repositories;

import be.java.gestiondossierjudiciare.domain.entities.Personne;
import be.java.gestiondossierjudiciare.domain.entities.Plainte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import java.util.Optional;

@Repository
public interface PlainteRepository extends JpaRepository<Plainte, Long>, JpaSpecificationExecutor<Plainte> {

    @Query("select p from Plainte p where p.plaignant.id = :id")
    List<Plainte> findByPlaignantId(Long id);

    @Query("""
            SELECT p FROM Plainte p WHERE p.numeroDossier ilike :numeroDossier
            """)
    Optional<Plainte> findByNumeroDossier(String numeroDossier);

    @Query("SELECT p FROM Plainte p WHERE :personne MEMBER OF p.personnesConcernees")
    List<Plainte> findByPersonnesConcernees(Personne personne);

}
