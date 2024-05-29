package be.tftic.java.bll.services.impls;


import be.tftic.java.bll.services.AuditionService;
import be.tftic.java.bll.services.PersonneService;
import be.tftic.java.bll.specifications.AuditionSpecification;
import be.tftic.java.common.models.requests.create.AuditionCreateRequest;
import be.tftic.java.dal.repositories.AuditionRepository;
import be.tftic.java.dal.repositories.PersonneRepository;
import be.tftic.java.dal.repositories.PlainteRepository;
import be.tftic.java.domain.entities.Audition;
import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.entities.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
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

	public List<Audition> findAllAudition(Long plainteId) {
		Plainte plainte = getComplaint(plainteId);

		return auditionRepository.findByPlainte(plainte);
	}

	@Override
	public List<Audition> findAll() {
		return auditionRepository.findAll();
	}

	@Override
	public List<Audition> findAuditionByCriteria(Personne personne, LocalDate lowerBound, LocalDate upperBound) {
		Specification<Audition> spec = getSpecification( lowerBound, upperBound);
		return auditionRepository.findAll(spec);
	}

	private Plainte getComplaint(Long plainteId){
		return plainteRepository.findById(plainteId).orElseThrow(
				() -> new RuntimeException("La plainte n'existe pas")
		);
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
