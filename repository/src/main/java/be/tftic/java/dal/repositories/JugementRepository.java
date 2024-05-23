package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Jugement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JugementRepository extends JpaRepository<Jugement, Long>, JpaSpecificationExecutor<Jugement> {
}
