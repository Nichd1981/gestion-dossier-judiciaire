package be.java.gestiondossierjudiciare.bll.services.impls;

import be.java.gestiondossierjudiciare.bll.services.ConnexionService;
import be.java.gestiondossierjudiciare.dal.repositories.ConnexionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConnexionServiceImpl implements ConnexionService {

    private final ConnexionRepository connexionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO : gérer les exceptions.
        return connexionRepository.getUserByUsername(username).orElseThrow();
    }
}
