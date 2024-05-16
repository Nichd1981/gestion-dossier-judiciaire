package be.java.gestiondossierjudiciare.dal.repositories;

import be.java.gestiondossierjudiciare.domain.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {

}
