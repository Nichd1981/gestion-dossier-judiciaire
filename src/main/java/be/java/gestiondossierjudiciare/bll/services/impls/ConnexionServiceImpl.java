package be.java.gestiondossierjudiciare.bll.services.impls;

import be.java.gestiondossierjudiciare.bll.services.ConnexionService;
import be.java.gestiondossierjudiciare.dal.repositories.ConnexionRepository;
import be.java.gestiondossierjudiciare.domain.entities.Connexion;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConnexionServiceImpl implements ConnexionService {

    private final ConnexionRepository connexionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        TODO : gerer les exceptions.
        return connexionRepository.getUserByUsername(username).orElseThrow();
    }

    @Override
    public Connexion login(Connexion connexion) {
        Connexion existingUser = connexionRepository.getUserByUsername(connexion.getEmail()).orElseThrow();

        if (!passwordEncoder.matches(connexion.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        return existingUser;
    }
}
