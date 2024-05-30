package be.tftic.java.il.configs;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
/**
 * Classe de configuration de la sécurité JWT.
 *
 * Cette classe est annotée avec @Component et @ConfigurationProperties
 * pour indiquer à Spring de créer et gérer une instance unique de cette classe, et de la configurer
 * à partir des propriétés de l'application dans le fichier de configuration ou dans les variables
 * d'environnement. Elle est utilisée pour stocker et gérer les propriétés de sécurité JWT, telles que
 * l'algorithme de chiffrement, la clé secrète, et la durée de vie des jetons JWT.
 *
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Getter @Setter
public class JwtConfig {

    /**
     * Algorithme de chiffrement utilisé pour les jetons JWT.
     *
     * Par défaut, cette propriété est définie à "HS256", qui est l'algorithme de chiffrement symétrique
     * HMAC-SHA256.
     */
    private String algorithm;

    /**
     * Clé secrète utilisée pour les jetons JWT.
     *
     * Cette propriété doit être définie à une valeur aléatoire et secrète, et ne doit pas être stockée
     * dans le code source ou dans un fichier de configuration accessible en lecture. Elle peut être
     * stockée dans un coffre-fort de clés ou dans une variable d'environnement.
     */
    private String secret;

    /**
     * Durée de vie des jetons JWT, en secondes.
     *
     * Par défaut, cette propriété est définie à 86400 secondes, soit 24 heures.
     */
    private long expireAt;

    /**
     * Clé secrète au format SecretKey de Java.
     *
     * Cette propriété est initialisée à partir de la propriété "secret" dans la méthode
     * init(), en utilisant la classe SecretKeySpec de Java.
     */
    private SecretKey secretKey;

    /**
     * Méthode d'initialisation de la classe JwtConfig.
     *
     * Cette méthode est annotée avec @PostConstruct pour indiquer à Spring de l'appeler
     * après la création et l'injection de dépendances de l'instance de la classe JwtConfig.
     * Elle est utilisée pour initialiser la propriété secretKey à partir de la propriété
     * "secret".
     */
    @PostConstruct
    public void init() {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), algorithm);
    }
}
