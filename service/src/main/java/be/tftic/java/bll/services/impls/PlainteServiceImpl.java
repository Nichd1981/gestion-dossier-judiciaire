package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.JugementService;
import be.tftic.java.bll.services.PersonneService;
import be.tftic.java.bll.services.PlainteService;
import be.tftic.java.bll.specifications.PlainteSpecification;
import be.tftic.java.common.models.requests.ClotureEnqueteRequest;
import be.tftic.java.common.models.requests.PlainteCreateRequest;
import be.tftic.java.common.models.requests.PlainteFilterRequest;
import be.tftic.java.common.models.responses.PlainteShortResponse;
import be.tftic.java.dal.repositories.PlainteRepository;
import be.tftic.java.domain.entities.Personne;
import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.entities.Utilisateur;
import be.tftic.java.domain.enums.Statut;
import be.tftic.java.domain.enums.TypePlainte;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlainteServiceImpl implements PlainteService {

    private final PlainteRepository plainteRepository;
    private final PersonneService personneService;
    private final JugementService jugementService;

    @Override
    public List<PlainteShortResponse> findByPlaignantId() {
        Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return plainteRepository.findByPlaignantId(user.getPersonne().getId())
                .stream()
                .map(PlainteShortResponse::fromEntity)
                .toList();
    }

    @Override
    public List<PlainteShortResponse> findByPersonneConcernee() {
        Utilisateur user = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return plainteRepository.findByPersonnesConcernees(user.getPersonne())
                .stream()
                .map(PlainteShortResponse::fromEntity)
                .toList();
    }

    @Override
    public List<PlainteShortResponse> findAll() {
        return plainteRepository.findAll()
                .stream()
                .map(PlainteShortResponse::fromEntity)
                .toList();
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
    public List<PlainteShortResponse> findByCriteria(PlainteFilterRequest f) {
        Specification<Plainte> spec = getSpecification(f.getNumeroDossier(), f.getDateLowerBound(), f.getDateUpperBound(), f.getStatut(), null, null);

        return plainteRepository.findAll(spec)
                .stream()
                .map(PlainteShortResponse::fromEntity)
                .toList();
    }

    @Override
    public Plainte create(PlainteCreateRequest form) {

        Plainte plainte = form.toEntity();
        plainte.setPlaignant(personneService.findById(form.idPlaignant()));
        plainte.setAgentTraitant(personneService.findById(form.idAgentTraitant()));
        form.idConcernes().forEach(id -> {
            Personne personne = personneService.findById(id);
            plainte.getPersonnesConcernees().add(personne);
        });

        return plainteRepository.save(plainte);
    }

    @Override
    public void ouvrirEnquete(Long id) {
        Plainte toUpdate = this.findById(id);

        toUpdate.setStatut(Statut.EN_COURS);
        plainteRepository.save(toUpdate);
    }

    @Override
    public void cloturerEnquete(ClotureEnqueteRequest form) {
        Plainte toUpdate = this.findById(form.plainteId());

        // Si la plainte n'est pas encore ouverte, exception!
        if (toUpdate.getStatut() != Statut.EN_COURS) {
            //TODO : custom exception
            throw new RuntimeException("La plainte doit être \"En cours\" pour pouvoir être clôturée.");
        }

        toUpdate.setStatut(Statut.CLOTUREE);
        toUpdate.setTypePlainte(TypePlainte.valueOf(form.type()));
        // Si le type de plainte est Délit ou Crime, créer un Jugement associé
        if (TypePlainte.valueOf(form.type()) == TypePlainte.DELIT || TypePlainte.valueOf(form.type()) == TypePlainte.CRIME) {
            jugementService.create(form.plainteId());
        }
        plainteRepository.save(toUpdate);
    }

    @Override
    public List<PlainteShortResponse> findByPlaignantIdWithCriteria(PlainteFilterRequest f) {
        Utilisateur plaignant = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        Specification <Plainte> spec = getSpecification(f.getNumeroDossier(),
                                                        f.getDateLowerBound(),
                                                        f.getDateUpperBound(),
                                                        f.getStatut(),
                                                        f.getType(),
                                                        plaignant.getPersonne());
        return plainteRepository.findAll(spec)
                .stream()
                .map(PlainteShortResponse::fromEntity)
                .toList();
    }

    private Specification<Plainte> getSpecification(String numeroDossier, LocalDate lowerBound, LocalDate upperBound, String statut, String type, Personne plaignant) {

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
        if(statut != null && !statut.isBlank()){
            spec = spec.and(PlainteSpecification.getByStatut(Statut.valueOf(statut)));
        }
        if(type != null && !type.isBlank()){
            spec = spec.and(PlainteSpecification.getByType(TypePlainte.valueOf(type)));
        }
        if(plaignant != null){
            spec = spec.and(PlainteSpecification.getByPlaignant(plaignant));
        }
        return spec;
    }
}
