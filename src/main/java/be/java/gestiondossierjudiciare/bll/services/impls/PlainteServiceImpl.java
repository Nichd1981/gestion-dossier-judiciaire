package be.java.gestiondossierjudiciare.bll.services.impls;

import be.java.gestiondossierjudiciare.api.forms.PlainteCreateForm;
import be.java.gestiondossierjudiciare.bll.services.PersonneService;
import be.java.gestiondossierjudiciare.bll.services.PlainteService;
import be.java.gestiondossierjudiciare.bll.specifications.PlainteSpecification;
import be.java.gestiondossierjudiciare.dal.repositories.PersonneRepository;
import be.java.gestiondossierjudiciare.dal.repositories.PlainteRepository;
import be.java.gestiondossierjudiciare.domain.entities.Personne;
import be.java.gestiondossierjudiciare.domain.entities.Plainte;
import be.java.gestiondossierjudiciare.domain.enums.Statut;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlainteServiceImpl implements PlainteService {

    private final PlainteRepository plainteRepository;
    private final PersonneService personneService;

    @Override
    public List<Plainte> findByPlaignantId(Long id) {
        return plainteRepository.findByPlaignantId(id);
    }

    @Override
    public List<Plainte> findByPersonneConcernee(Personne personne) {
        return plainteRepository.findByPersonnesConcernees(personne);
    }

    @Override
    public List<Plainte> findAll() {
        return plainteRepository.findAll();
    }

    @Override
    public Plainte findById(Long id) {
        return plainteRepository.findById(id).orElseThrow(
                // TODO : gestion exceptions custom
                () -> new RuntimeException("Le plainte n'existe pas")
        );
    }

    @Override
    public Plainte findByNumeroDossier(String numeroDossier) {
        return plainteRepository.findByNumeroDossier(numeroDossier).orElseThrow(
                // TODO : gestion exceptions custom
                () -> new RuntimeException("Le plainte n'existe pas")
        );
    }

    @Override
    public List<Plainte> findByCriteria(String numeroDossier, LocalDate lowerBound, LocalDate upperBound, String statut) {
        Specification<Plainte> spec = getSpecification(numeroDossier, lowerBound, upperBound, statut);
        return plainteRepository.findAll(spec);
    }

    @Override
    public Plainte create(PlainteCreateForm form) {

        Plainte plainte = form.toEntity();
        plainte.setPlaignant(personneService.findById(form.idPlaignant()));
        plainte.setAgentTraitant(personneService.findById(form.idAgentTraitant()));
        form.idConcernes().forEach(id -> {
            Personne personne = personneService.findById(id);
            plainte.getPersonnesConcernees().add(personne);
        });

        return plainteRepository.save(plainte);
    }

    private Specification<Plainte> getSpecification(String numeroDossier, LocalDate lowerBound, LocalDate upperBound, String statut) {

        Specification<Plainte> spec = Specification.where(null);
        if(!numeroDossier.isBlank()){
            spec = spec.and(PlainteSpecification.getByNumeroDossier(numeroDossier));
        }
        if(lowerBound != null){
            spec = spec.and(PlainteSpecification.getByDateLowerBound(lowerBound));
        }
        if(upperBound != null){
            spec = spec.and(PlainteSpecification.getByDateUpperBound(upperBound));
        }
        if(!statut.isBlank()){
            spec = spec.and(PlainteSpecification.getByStatut(Statut.valueOf(statut)));
        }
        return spec;
    }

}
