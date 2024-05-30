package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Audition;
import be.tftic.java.domain.entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AuditionRepository extends JpaRepository<Audition, Long>, JpaSpecificationExecutor<Audition> {

	@Query("SELECT a FROM Audition a WHERE a.complaint = :complaint")
	List<Audition> findByComplaint(Complaint complaint);

}
