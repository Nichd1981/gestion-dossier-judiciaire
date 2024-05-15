package be.java.gestiondossierjudiciare.dal.repositories;

import be.java.gestiondossierjudiciare.domain.entities.Plainte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlainteRepository extends JpaRepository<Plainte, Long> {
}
