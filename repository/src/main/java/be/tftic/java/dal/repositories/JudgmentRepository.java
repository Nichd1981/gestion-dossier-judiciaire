package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Judgment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JudgmentRepository extends JpaRepository<Judgment, Long>, JpaSpecificationExecutor<Judgment> {
}
