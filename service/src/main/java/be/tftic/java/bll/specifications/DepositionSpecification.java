package be.tftic.java.bll.specifications;

import be.tftic.java.domain.entities.Deposition;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class DepositionSpecification {

    public static Specification<Deposition> getByDateLowerBound(LocalDate lowerBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateDeposition"), lowerBound));
    }

    public static Specification<Deposition> getByDateUpperBound(LocalDate upperBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateDeposition"), upperBound));
    }

    public static Specification<Deposition> getByKeyword(String keyword) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("deposition")), "%" + keyword.toLowerCase() + "%"));
    }
}
