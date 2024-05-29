package be.tftic.java.bll.services.impls;


import be.tftic.java.bll.services.AuditionService;
import be.tftic.java.bll.specifications.AuditionSpecification;
import be.tftic.java.bll.specifications.PlainteSpecification;
import be.tftic.java.dal.repositories.AuditionRepository;
import be.tftic.java.dal.repositories.DepositionRepository;
import be.tftic.java.dal.repositories.PlainteRepository;
import be.tftic.java.domain.entities.Audition;
import be.tftic.java.domain.entities.Deposition;
import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.enums.Statut;
import be.tftic.java.domain.enums.TypePlainte;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditionServiceImpl implements AuditionService {

	private final PlainteRepository plainteRepository;
	private final AuditionRepository AuditionRepository;


	public List<Audition> findAllAudition(Long id) {
		Plainte plainte = plainteRepository.findById(id).orElseThrow(
				() -> new RuntimeException("La plainte n'existe pas")
		);

		return AuditionRepository.findByPlainte(plainte);
	}

	@Override
	public List<Audition> findAll() {
		return AuditionRepository.findAll();
	}

	@Override
	public List<Audition> findAuditionByCriteria(Personne personne, LocalDate lowerBound, LocalDate upperBound) {
		Specification<Audition> spec = getSpecification( lowerBound, upperBound);
		return AuditionRepository.findAll();
	}


	private Specification<Audition> getSpecification(LocalDate lowerBound, LocalDate upperBound) {

		Specification<Audition> spec = Specification.where(null);

		if(lowerBound != null){
			spec = spec.and(AuditionSpecification.getByDateLowerBound(lowerBound));
		}
		if(upperBound != null){
			spec = spec.and(AuditionSpecification.getByDateUpperBound(upperBound));
		}
		return spec;
	}

}
