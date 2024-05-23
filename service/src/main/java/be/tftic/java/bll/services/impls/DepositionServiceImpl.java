package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.DepositionService;
import be.tftic.java.dal.repositories.DepositionRepository;
import be.tftic.java.dal.repositories.PlainteRepository;
import be.tftic.java.domain.entities.Deposition;
import be.tftic.java.domain.entities.Plainte;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositionServiceImpl implements DepositionService {
    private final PlainteRepository plainteRepository;
    private final DepositionRepository depositionRepository;

    @Override
    public List<Deposition> findAllDeposition(Long id) {
        Plainte plainte = plainteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("La plainte n'existe pas")
        );

        return depositionRepository.findByPlainte(plainte);
    }
}
