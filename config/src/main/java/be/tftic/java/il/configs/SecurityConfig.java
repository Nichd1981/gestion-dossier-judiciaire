package be.tftic.java.il.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Classe de configuration pour la sécurité de l'application.
 * Cette classe configure la gestion de la sécurité web et de la sécurité des méthodes.
 *
 * Cette classe utilise les annotations {@link Configuration}, {@link EnableWebSecurity},
 * et {@link EnableMethodSecurity} pour activer la sécurité au niveau de l'application.
 * Elle définit des beans pour gérer l'authentification, le filtrage de sécurité,
 * et l'encodage des mots de passe.
 */
@Configuration
@EnableWebSecurity(debug = false)
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Définit le gestionnaire d'authentification pour l'application.
     *
     * @param config la configuration d'authentification
     * @return un gestionnaire d'authentification
     * @throws Exception si une erreur survient lors de la configuration de l'authentification
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configure la chaîne de filtres de sécurité pour les requêtes HTTP.
     *
     * @param http la configuration de sécurité HTTP
     * @return la chaîne de filtres de sécurité configurée
     * @throws Exception si une erreur survient lors de la configuration de la sécurité HTTP
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(r -> r.anyRequest().permitAll());

        return http.build();
    }

    /**
     * Définit l'encodeur de mot de passe pour l'application.
     *
     * @return un encodeur de mot de passe
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

