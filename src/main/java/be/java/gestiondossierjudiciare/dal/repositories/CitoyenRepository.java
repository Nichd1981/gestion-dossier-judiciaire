package be.java.gestiondossierjudiciare.dal.repositories;

import be.java.gestiondossierjudiciare.domain.entities.Citoyen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitoyenRepository extends JpaRepository<Citoyen, String> {

}
