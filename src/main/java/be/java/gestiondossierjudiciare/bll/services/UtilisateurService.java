package be.java.gestiondossierjudiciare.bll.services;

import be.java.gestiondossierjudiciare.domain.entities.Utilisateur;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UtilisateurService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    Utilisateur login(Utilisateur utilisateur);
}
