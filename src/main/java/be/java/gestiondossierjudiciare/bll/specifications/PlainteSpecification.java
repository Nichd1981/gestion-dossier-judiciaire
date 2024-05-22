package be.java.gestiondossierjudiciare.bll.specifications;

import be.java.gestiondossierjudiciare.domain.entities.Personne;
import be.java.gestiondossierjudiciare.domain.entities.Plainte;
import be.java.gestiondossierjudiciare.domain.enums.Statut;
import be.java.gestiondossierjudiciare.domain.enums.TypePlainte;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PlainteSpecification {

    public static Specification<Plainte> getByNumeroDossier(String numeroDossier) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("numeroDossier"), "%" + numeroDossier + "%"));
    }

    public static Specification<Plainte> getByDateLowerBound(LocalDate lowerBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("datePlainte"), lowerBound));
    }

    public static Specification<Plainte> getByDateUpperBound(LocalDate upperBound) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("datePlainte"), upperBound));
    }

    public static Specification<Plainte> getByStatut(Statut statut) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("statut"), statut));
    }

    public static Specification<Plainte> getByPlaignant(Personne plaignant){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("plaignant"), plaignant));
    }

    public static Specification<Plainte> getByType(TypePlainte type){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type));
    }

}
