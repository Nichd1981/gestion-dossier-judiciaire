package be.tftic.java.bll.specifications;

import be.tftic.java.domain.entities.Audition;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

public class AuditionSpecification {

	public static Specification<Audition> getByDateLowerBound(LocalDate lowerBound) {
		return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dateAudition"), lowerBound));
	}

	public static Specification<Audition> getByDateUpperBound(LocalDate upperBound) {
		return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dateAudition"), upperBound));
	}

	public static Specification<Audition> getByMotCle(String motCle){
		return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("depositionAudition"), "%"+motCle+"%"));
	}

}
