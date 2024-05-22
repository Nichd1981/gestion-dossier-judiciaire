package be.java.gestiondossierjudiciare.bll.services.impls;

import be.java.gestiondossierjudiciare.bll.services.JugementService;
import be.java.gestiondossierjudiciare.bll.services.PlainteService;
import be.java.gestiondossierjudiciare.dal.repositories.JugementRepository;
import be.java.gestiondossierjudiciare.dal.repositories.PlainteRepository;
import be.java.gestiondossierjudiciare.domain.entities.Jugement;
import lombok.RequiredArgsConstructor;
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

    @Override
    public List<Jugement> findAll(Long plainteId) {
        return List.of();
    }

    @Override
    public List<Jugement> findWithCriteria(Long plainteId, LocalDate lowerBound, LocalDate upperBound, String keyWord) {
        return List.of();
    }

    @Override
    public void cloturerJugement(String decision, String commentaire) {

    }
}
