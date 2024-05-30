package be.tftic.java.bll.services;

import be.tftic.java.common.models.responses.UserTokenResponse;
import be.tftic.java.domain.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    UserTokenResponse login(User user);
}
