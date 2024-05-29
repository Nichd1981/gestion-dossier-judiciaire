package be.tftic.java.bll.services.impls;


import be.tftic.java.bll.services.AuditionService;
import be.tftic.java.bll.specifications.AuditionSpecification;
import be.tftic.java.bll.specifications.PlainteSpecification;
import be.tftic.java.common.models.requests.AuditionFilterRequest;
import be.tftic.java.common.models.responses.AuditionShortResponse;
import be.tftic.java.dal.repositories.AuditionRepository;
import be.tftic.java.dal.repositories.DepositionRepository;
import be.tftic.java.dal.repositories.PlainteRepository;
import be.tftic.java.domain.entities.*;
import be.tftic.java.domain.enums.Statut;
import be.tftic.java.domain.enums.TypePlainte;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditionServiceImpl implements AuditionService {

	private final PlainteRepository plainteRepository;
	private final AuditionRepository AuditionRepository;

	public List<AuditionShortResponse> findAllAudition(Long id) {
		Plainte plainte = plainteRepository.findById(id).orElseThrow(
				() -> new RuntimeException("La plainte n'existe pas")
		);

		return AuditionRepository.findByPlainte(plainte)
				.stream()
				.map(AuditionShortResponse::fromEntity)
				.toList();
	}

	@Override
	public List<AuditionShortResponse> findAll() {
		return AuditionRepository.findAll()
				.stream()
				.map(AuditionShortResponse::fromEntity)
				.toList();
	}

	@Override
	public List<AuditionShortResponse> findAuditionByCriteria(AuditionFilterRequest f) {
		Specification<Audition> spec = getSpecification(f.getDateLowerBound(), f.getDateUpperBound(), f.getKeyword());
		return AuditionRepository.findAll(spec)
				.stream()
				.map(AuditionShortResponse::fromEntity)
				.toList();
	}


	private Specification<Audition> getSpecification(LocalDate lowerBound, LocalDate upperBound, String keyword) {

		Specification<Audition> spec = Specification.where(null);

		if(lowerBound != null){
			spec = spec.and(AuditionSpecification.getByDateLowerBound(lowerBound));
		}
		if(upperBound != null){
			spec = spec.and(AuditionSpecification.getByDateUpperBound(upperBound));
		}
		if(keyword != null && !keyword.isBlank()) {
			spec = spec.and(AuditionSpecification.getByKeyword(keyword));
		}
		return spec;
	}

}
