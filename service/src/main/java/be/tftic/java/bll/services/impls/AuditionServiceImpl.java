package be.tftic.java.bll.services.impls;


import be.tftic.java.bll.services.AuditionService;
import be.tftic.java.bll.services.PersonneService;
import be.tftic.java.bll.specifications.AuditionSpecification;
import be.tftic.java.common.models.requests.create.AuditionCreateRequest;
import be.tftic.java.bll.specifications.PlainteSpecification;
import be.tftic.java.common.models.requests.filter.AuditionFilterRequest;
import be.tftic.java.common.models.responses.AuditionShortResponse;
import be.tftic.java.dal.repositories.AuditionRepository;
import be.tftic.java.dal.repositories.PersonneRepository;
import be.tftic.java.dal.repositories.PlainteRepository;
import be.tftic.java.domain.entities.Audition;
import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.entities.Utilisateur;
import be.tftic.java.domain.entities.*;
import be.tftic.java.domain.enums.Statut;
import be.tftic.java.domain.enums.TypePlainte;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditionServiceImpl implements AuditionService {

	private final PlainteRepository plainteRepository;
	private final AuditionRepository auditionRepository;
	private final PersonneService personneService;

	@Override
	public void create(AuditionCreateRequest request) {
		Audition audition = request.toEntity();

		Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Personne agent = user.getPersonne();
		audition.setAgentTraitant(agent);
		Personne citizen = personneService.findById(request.citizenId());
		audition.setConvoque(citizen);
		if (request.lawyerId() != null){
			Personne lawyer = personneService.findById(request.lawyerId());
			audition.setAvocat(lawyer);
		}

		Plainte plainte = getComplaint(request.complaintId());
		audition.setPlainte(plainte);

		auditionRepository.save(audition);
	}

    @Override
	public List<AuditionShortResponse> findAllAudition(Long id) {
		Plainte plainte = plainteRepository.findById(id).orElseThrow(
				() -> new RuntimeException("La plainte n'existe pas")
		);

		return auditionRepository.findByPlainte(plainte)
				.stream()
				.map(AuditionShortResponse::fromEntity)
				.toList();
	}

	@Override
	public List<AuditionShortResponse> findAll() {
		return auditionRepository.findAll()
				.stream()
				.map(AuditionShortResponse::fromEntity)
				.toList();
	}

	@Override
	public List<AuditionShortResponse> findAuditionByCriteria(AuditionFilterRequest f) {
		Specification<Audition> spec = getSpecification(f.getDateLowerBound(), f.getDateUpperBound(), f.getKeyword());
		return auditionRepository.findAll(spec)
				.stream()
				.map(AuditionShortResponse::fromEntity)
				.toList();
	}

    private Plainte getComplaint(Long plainteId){
		return plainteRepository.findById(plainteId).orElseThrow(
				() -> new RuntimeException("La plainte n'existe pas")
        );
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
