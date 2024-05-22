package be.tftic.java.il.configs;

import be.tftic.java.il.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtFilters extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userService;

    public JwtFilters(JwtUtils jwtUtils, UserDetailsService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer ")){
            String token = authorization.substring(7);
            if(jwtUtils.isValid(token)){
                String username = jwtUtils.getUsername(token);
                UserDetails user = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken upt = new UsernamePasswordAuthenticationToken(
                        user,
                        token,
                        user.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(upt);
            }
        }
        filterChain.doFilter(request,response);
    }
}
