package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.JugementService;
import be.tftic.java.bll.specifications.JugementSpecification;
import be.tftic.java.bll.specifications.PlainteSpecification;
import be.tftic.java.dal.repositories.JugementRepository;
import be.tftic.java.dal.repositories.PlainteRepository;
import be.tftic.java.domain.entities.Jugement;
import be.tftic.java.domain.entities.Plainte;
import be.tftic.java.domain.enums.JugementDecision;
import be.tftic.java.domain.enums.Statut;
import be.tftic.java.domain.enums.TypePlainte;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JugementServiceImpl implements JugementService {

    private final JugementRepository jugementRepository;
    private final PlainteRepository plainteRepository;

    @Override
    public void create(Long plainteId) {
        Jugement jugement = new Jugement();
        jugement.setDateJugement(LocalDateTime.now());
        jugement.setPlainte(plainteRepository.findById(plainteId).orElseThrow(
                () -> new RuntimeException("Plainte n'existe pas")
        ));
        jugementRepository.save(jugement);
    }

    /**
     * Liste tous les jugements liés à une plainte.
     * @param plainteId pour laquelle on veut lister les jugements.
     * @return
     */
    @Override
    public List<Jugement> findAllForPlainte(Long plainteId) {
       return this.findWithCriteria(plainteId, null, null, null, null, null);
    }

    /**
     * Liste tous les jugements liés à une plainte, avec filtre sur date, mot-clé et désicision
     * @param plainteId pour laquelle on veut lister les jugements
     * @return
     */
    @Override
    public List<Jugement> findWithCriteria(Long plainteId, String numeroDossier, LocalDate lowerBound, LocalDate upperBound, String keyWord, String decision) {
        Plainte plainte;
        if (plainteId != null){
            plainte = plainteRepository.findById(plainteId)
                    .orElseThrow(
                            () -> new RuntimeException("Plainte n'existe pas")
                    );
        } else {
            plainte = plainteRepository.findByNumeroDossier(numeroDossier)
                    .orElseThrow(
                            () -> new RuntimeException("Plainte n'existe pas")
                    );
        }

        Specification<Jugement> spec = getSpecification(plainte, lowerBound, upperBound, keyWord, decision);
        return jugementRepository.findAll(spec);
    }

    @Override
    public void cloturerJugement(String decision, String commentaire) {

    }

    private Specification<Jugement> getSpecification(Plainte plainte, LocalDate lowerBound, LocalDate upperBound, String keyWord, String decision){
        Specification<Jugement> spec = Specification.where(null);
        if(plainte != null){
            spec = spec.and(JugementSpecification.getByPlainte(plainte));
        }
        if(lowerBound != null){
            spec = spec.and(JugementSpecification.getByDateLowerBound(lowerBound));
        }
        if(upperBound != null){
            spec = spec.and(JugementSpecification.getByDateUpperBound(upperBound));
        }
        if(decision != null && !keyWord.isBlank()){
            spec = spec.and(JugementSpecification.getByKeyword(keyWord));
        }

        if(decision != null && !decision.isBlank()){
            spec = spec.and(JugementSpecification.getByDecision(JugementDecision.valueOf(decision)));
        }
        return spec;
    }
}
