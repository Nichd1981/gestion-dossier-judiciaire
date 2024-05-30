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
 * Classe de configuration de la sécurité de l'application.
 * Cette classe permet de configurer les différents éléments de la sécurité de
 * l'application, tels que l'authentification, l'autorisation, le chiffrement
 * des mots de passe, etc. Elle utilise les annotations @Configuration,
 * @EnableWebSecurity et @EnableMethodSecurity de Spring
 * Security pour activer et configurer les fonctionnalités de sécurité.
 *
 */
@Configuration
@EnableWebSecurity(debug = false)
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Crée un gestionnaire d'authentification pour l'application.
     * Cette méthode est annotée avec @Bean pour indiquer à Spring de
     * créer et gérer une instance unique de cette classe. Elle utilise la classe
     * AuthenticationConfiguration de Spring Security pour créer un
     * gestionnaire d'authentification à partir de la configuration de sécurité.
     *
     * @param config la configuration de sécurité de l'application
     * @return le gestionnaire d'authentification créé
     * @throws Exception en cas d'erreur de configuration ou d'initialisation
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    /**
     * Crée une chaîne de filtres de sécurité pour l'application.
     * Cette méthode est annotée avec @Bean pour indiquer à Spring de
     * créer et gérer une instance unique de cette classe. Elle utilise la classe
     * HttpSecurity de Spring Security pour créer une chaîne de filtres
     * de sécurité à partir de la configuration de sécurité. Dans cette implémentation,
     * elle désactive la protection CSRF et la gestion des CORS, et autorise tous les
     * accès HTTP aux ressources de l'application.
     *
     * @param http la configuration de sécurité HTTP de l'application
     * @return la chaîne de filtres de sécurité créée
     * @throws Exception en cas d'erreur de configuration ou d'initialisation
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(r -> r.anyRequest().permitAll());

        return http.build();
    }

    /**
     * Crée un encodeur de mots de passe pour l'application.
     * Cette méthode est annotée avec @Bean pour indiquer à Spring de
     * créer et gérer une instance unique de cette classe. Elle utilise la classe
     * BCryptPasswordEncoder de Spring Security pour créer un encodeur
     * de mots de passe à partir de la configuration de sécurité. Cet encodeur utilise
     * l'algorithme de hachage cryptographique BCrypt pour chiffrer les mots de passe
     * avant de les stocker dans la base de données.
     *
     * @return l'encodeur de mots de passe créé
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
