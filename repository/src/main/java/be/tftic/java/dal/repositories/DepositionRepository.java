package be.tftic.java.dal.repositories;

import be.tftic.java.domain.entities.Deposition;
import be.tftic.java.domain.entities.Plainte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositionRepository extends JpaRepository<Deposition, Long> {

    @Query("select d from Deposition d where d.plainte = :plainte")
    List<Deposition> findByPlainte(Plainte plainte);

}
