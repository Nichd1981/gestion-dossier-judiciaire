package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.UtilisateurService;
import be.tftic.java.common.models.responses.UtilisateurTokenResponse;
import be.tftic.java.dal.repositories.UtilisateurRepository;
import be.tftic.java.domain.entities.Utilisateur;
import be.tftic.java.il.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        TODO : gerer les exceptions.
        return utilisateurRepository.getUserByUsername(username).orElseThrow();
    }

    @Override
    public UtilisateurTokenResponse login(Utilisateur utilisateur) {
        Utilisateur existingUser = utilisateurRepository.getUserByUsername(utilisateur.getEmail()).orElseThrow();
        UtilisateurTokenResponse dto = UtilisateurTokenResponse.fromEntity(existingUser);
        String token = jwtUtils.generateToken(utilisateur);
        dto.setToken(token);

        if (!passwordEncoder.matches(utilisateur.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return dto;
    }
}
