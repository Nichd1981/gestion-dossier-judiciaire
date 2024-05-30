package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Person;
import be.tftic.java.domain.entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long>, JpaSpecificationExecutor<Complaint> {

    @Query("SELECT c FROM Complaint c where c.complainant.id = :id")
    List<Complaint> findByComplainantId(Long id);

    @Query(" SELECT c FROM Complaint c WHERE c.fileNumber ILIKE :fileNumber")
    Optional<Complaint> findByFileNumber(String fileNumber);

    @Query("SELECT c FROM Complaint c WHERE :person MEMBER OF c.personConcerned")
    List<Complaint> findByPersonConcerned(Person person);

}
