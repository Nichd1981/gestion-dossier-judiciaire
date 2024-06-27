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
 * Classe de filtre de requêtes HTTP pour l'authentification JWT.
 * Cette classe étend la classe OncePerRequestFilter de Spring pour créer un filtre de
 * requêtes HTTP qui est exécuté une seule fois par requête. Elle est utilisée pour extraire le jeton
 * JWT de l'en-tête d'autorisation de la requête HTTP, vérifier sa validité, et configurer le contexte
 * de sécurité de Spring avec les informations d'authentification de l'utilisateur.
 *
 */
@Configuration
public class JwtFilters extends OncePerRequestFilter {

    /**
     * Utilitaire pour la gestion des jetons JWT.
     */
    private final JwtUtils jwtUtils;

    /**
     * Service pour la gestion des utilisateurs.
     */
    private final UserDetailsService userService;

    /**
     * Constructeur de la classe JwtFilters.
     *
     * @param jwtUtils l'utilitaire pour la gestion des jetons JWT
     * @param userService le service pour la gestion des utilisateurs
     */
    public JwtFilters(JwtUtils jwtUtils, UserDetailsService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    /**
     * Check if the request should not be filtered.
     * The request should not be filtered if the servlet path starts with auth/login.
     * @param request The request to check.
     * @return True if the request should not be filtered, false otherwise.
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/auth/login");
    }

    /**
     * Méthode exécutée par le filtre de requêtes HTTP pour l'authentification JWT.
     * Cette méthode est appelée par la classe OncePerRequestFilter de Spring pour chaque
     * requête HTTP entrante. Elle extrait le jeton JWT de l'en-tête d'autorisation de la requête HTTP,
     * vérifie sa validité à l'aide de l'utilitaire JwtUtils, et configure le contexte de
     * sécurité de Spring avec les informations d'authentification de l'utilisateur à l'aide du service
     * UserDetailsService. Si le jeton JWT est invalide ou si l'utilisateur n'est pas trouvé,
     * la méthode ne fait rien et laisse la chaîne de filtres de sécurité de Spring traiter la requête HTTP.
     *
     * @param request la requête HTTP entrante
     * @param response la réponse HTTP sortante
     * @param filterChain la chaîne de filtres de sécurité de Spring
     * @throws ServletException en cas d'erreur de traitement de la requête HTTP
     * @throws IOException en cas d'erreur d'entrée/sortie
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            if (!token.isEmpty()) {
                if (jwtUtils.isValid(token)) {
                    System.out.println("valide");
                    String username = jwtUtils.getUsername(token);
                    UserDetails user = userService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken upt = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(upt);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
