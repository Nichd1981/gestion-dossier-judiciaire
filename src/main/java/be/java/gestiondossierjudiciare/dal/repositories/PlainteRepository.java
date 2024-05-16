package be.java.gestiondossierjudiciare.dal.repositories;

import be.java.gestiondossierjudiciare.domain.entities.Plainte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlainteRepository extends JpaRepository<Plainte, Long> {

    @Query("""
            SELECT p FROM Plainte p WHERE p.numeroDossier ilike :numeroDossier
            """)
    Optional<Plainte> findByNumeroDossier(String numeroDossier);

}
