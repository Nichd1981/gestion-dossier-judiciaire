package be.tftic.java.bll.services;

import be.tftic.java.common.models.responses.UtilisateurTokenResponse;
import be.tftic.java.domain.entities.Utilisateur;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UtilisateurService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    UtilisateurTokenResponse login(Utilisateur utilisateur);
}
