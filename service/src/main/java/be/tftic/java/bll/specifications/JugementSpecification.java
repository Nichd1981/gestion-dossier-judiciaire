package be.tftic.java.bll.specifications;

import be.tftic.java.domain.entities.Jugement;
import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.enums.JugementDecision;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class JugementSpecification {

    public static Specification<Jugement> getByPlainte(Plainte p){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("plainte"), p));
    }

    public static Specification<Jugement> getByDateLowerBound(LocalDate lowerBound){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateJugement"), lowerBound));
    }

    public static Specification<Jugement> getByDateUpperBound(LocalDate upperBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dateJugement"), upperBound));
    }

    public static Specification<Jugement> getByKeyword(String keyword) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("commentaire"), "%" + keyword + "%"));
    }

    public static Specification<Jugement> getByDecision(JugementDecision decision) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("jugementDecision"), decision));
    }

}
