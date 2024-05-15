package be.java.gestiondossierjudiciare.bll.services;

import be.java.gestiondossierjudiciare.domain.entities.Connexion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ConnexionService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    Connexion login(Connexion connexion);
}
