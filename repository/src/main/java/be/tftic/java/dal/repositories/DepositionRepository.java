package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Deposition;
import be.tftic.java.domain.entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DepositionRepository extends JpaRepository<Deposition, Long>, JpaSpecificationExecutor<Deposition> {

    @Query("SELECT d FROM Deposition d WHERE d.complaint = :complaint")
    List<Deposition> findByComplaint(Complaint complaint);

}
