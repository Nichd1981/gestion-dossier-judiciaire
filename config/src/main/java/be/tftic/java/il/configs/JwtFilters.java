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

/**
 * Classe de configuration pour les filtres JWT qui traite chaque requête HTTP une seule fois.
 * Elle valide le jeton JWT à partir de l'en-tête "Authorization" et définit l'authentification
 * dans le contexte de sécurité si le jeton est valide.
 *
 * <p>Ce filtre vérifie la présence d'un jeton JWT dans l'en-tête Authorization des requêtes HTTP
 * entrantes. Si le jeton est présent et valide, il extrait le nom d'utilisateur du jeton, charge
 * les détails de l'utilisateur à partir de UserDetailsService et définit l'authentification dans
 * le SecurityContextHolder.</p>
 *
 * <p>Étend {@link OncePerRequestFilter} pour s'assurer que ce filtre est exécuté une seule fois
 * par requête.</p>
 *
 * @see OncePerRequestFilter
 */
@Configuration
public class JwtFilters extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userService;

    /**
     * Construit une nouvelle instance de JwtFilters.
     *
     * @param jwtUtils une instance de {@link JwtUtils} pour les opérations JWT
     * @param userService une instance de {@link UserDetailsService} pour charger les détails de l'utilisateur
     */
    public JwtFilters(JwtUtils jwtUtils, UserDetailsService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    /**
     * Filtre chaque requête HTTP pour vérifier la validité d'un jeton JWT dans l'en-tête "Authorization".
     * Si un jeton valide est trouvé, il définit l'authentification dans le contexte de sécurité.
     *
     * @param request l'objet {@link HttpServletRequest}
     * @param response l'objet {@link HttpServletResponse}
     * @param filterChain l'objet {@link FilterChain}
     * @throws ServletException si une erreur survient lors du processus de filtrage
     * @throws IOException si une erreur d'entrée/sortie survient lors du processus de filtrage
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            if (jwtUtils.isValid(token)) {
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
        filterChain.doFilter(request, response);
    }
}


