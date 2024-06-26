package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Audition;
import be.tftic.java.domain.entities.Plainte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuditionRepository extends JpaRepository<Audition, Long> {

	@Query("select d from Audition d where d.plainte = :plainte")
	List<Audition> findByPlainte(Plainte plainte);

}
