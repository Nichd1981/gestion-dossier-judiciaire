package be.java.gestiondossierjudiciare.dal.repositories;

import be.java.gestiondossierjudiciare.domain.entities.Plainte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlainteRepository extends JpaRepository<Plainte, Long> {

    @Query("select p from Plainte p where p.plaignant = :id")
    List<Plainte> findByPlaignantId(Long id);
}
