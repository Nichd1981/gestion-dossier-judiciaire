package be.tftic.java.il.configs;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;

/**
 * Configuration pour les paramètres JWT.
 *
 * Cette classe lit les propriétés JWT à partir des propriétés de configuration de l'application
 * en utilisant le préfixe "jwt". Elle initialise également la clé secrète pour l'algorithme spécifié.
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtConfig {

    /**
     * L'algorithme utilisé pour le chiffrement JWT.
     */
    private String algorithm;

    /**
     * La clé secrète utilisée pour signer le JWT.
     */
    private String secret;

    /**
     * La durée d'expiration du JWT en millisecondes.
     */
    private long expireAt;

    /**
     * La clé secrète générée à partir du secret et de l'algorithme spécifié.
     */
    private SecretKey secretKey;

    /**
     * Méthode d'initialisation appelée après l'injection des propriétés.
     *
     * Cette méthode crée une instance de SecretKey en utilisant le secret et l'algorithme spécifiés.
     */
    @PostConstruct
    public void init() {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), algorithm);
    }
}

