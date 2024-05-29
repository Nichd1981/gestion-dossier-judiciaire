package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.DepositionService;
import be.tftic.java.bll.specifications.DepositionSpecification;
import be.tftic.java.common.models.requests.DepositionFilterRequest;
import be.tftic.java.common.models.responses.DepositionShortResponse;
import be.tftic.java.dal.repositories.DepositionRepository;
import be.tftic.java.dal.repositories.PlainteRepository;
import be.tftic.java.domain.entities.Deposition;
import be.tftic.java.domain.entities.Plainte;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepositionServiceImpl implements DepositionService {
    private final PlainteRepository plainteRepository;
    private final DepositionRepository depositionRepository;

    @Override
    public List<DepositionShortResponse> findAllDeposition(Long id) {
        Plainte plainte = plainteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("La plainte n'existe pas")
        );

        return depositionRepository.findByPlainte(plainte)
                .stream()
                .map(DepositionShortResponse::fromEntity)
                .toList();
    }

    @Override
    public List<DepositionShortResponse> findByCriteria(DepositionFilterRequest f) {
        return depositionRepository.findAll(getSpecification(f))
                .stream()
                .map(DepositionShortResponse::fromEntity)
                .toList();
    }


    private Specification<Deposition> getSpecification(DepositionFilterRequest f) {
        Specification<Deposition> spec = Specification.where(null);

        if(f.getDateLowerBound() != null) {
            spec = spec.and(DepositionSpecification.getByDateLowerBound(f.getDateLowerBound()));
        }

        if(f.getDateUpperBound() != null) {
            spec = spec.and(DepositionSpecification.getByDateUpperBound(f.getDateUpperBound()));
        }

        if (f.getKeyword() != null) {
            spec = spec.and(DepositionSpecification.getByKeyword(f.getKeyword() ));
        }

        return spec;
    }
}
