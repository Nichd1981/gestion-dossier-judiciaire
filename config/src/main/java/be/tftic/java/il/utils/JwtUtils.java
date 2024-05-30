package be.tftic.java.il.utils;

import be.tftic.java.domain.entities.User;
import be.tftic.java.il.configs.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Classe utilitaire pour la gestion des jetons JSON Web Token (JWT).
 * Cette classe permet de générer des jetons JWT pour l'authentification et
 * l'autorisation des utilisateurs, ainsi que de vérifier et d'extraire des
 * informations à partir de ces jetons. Elle utilise la bibliothèque
 * java-jwt de Auth0 pour la création et la vérification des jetons.
 *
 */
@Component
public class JwtUtils {

    /**
     * Configuration de la sécurité JWT.
     * Cette configuration est injectée automatiquement par Spring à partir de
     * la classe JwtConfig. Elle contient notamment la clé secrète
     * utilisée pour signer les jetons et la durée de vie des jetons.
     */
    private final JwtConfig config;

    /**
     * Constructeur de jetons JWT.
     * Ce constructeur est initialisé avec la clé secrète de la configuration
     * et est utilisé pour créer de nouveaux jetons JWT.
     */
    private final JwtBuilder builder;

    /**
     * Parseur de jetons JWT.
     * Ce parseur est initialisé avec la clé secrète de la configuration et est
     * utilisé pour vérifier et extraire des informations à partir de jetons JWT
     * existants.
     */
    private final JwtParser parser;

    /**
     * Constructeur de la classe JwtUtils.
     * Ce constructeur est utilisé par Spring pour créer une instance de la
     * classe JwtUtils et injecter automatiquement la configuration
     * de sécurité JWT.
     *
     * @param config la configuration de la sécurité JWT
     */
    public JwtUtils(JwtConfig config) {
        this.config = config;
        this.builder = Jwts.builder().signWith(config.getSecretKey());
        this.parser = Jwts.parserBuilder().setSigningKey(config.getSecretKey()).build();
    }

    /**
     * Génère un jeton JWT pour un utilisateur donné.
     * Cette méthode utilise le constructeur de jetons JWT pour créer un nouveau
     * jeton à partir des informations de l'utilisateur (notamment son nom
     * d'utilisateur et son identifiant). Elle définit également la date d'émission
     * et la date d'expiration du jeton à partir de la configuration de sécurité
     * JWT.
     *
     * @param c l'utilisateur pour lequel générer le jeton JWT
     * @return le jeton JWT généré pour l'utilisateur
     */

    public String generateToken(User c){
        return builder
                .setSubject(c.getUsername())
                .claim("id", c.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + config.getExpireAt() * 1000L))
                .compact();
    }

    /**
     * Extrait les revendications d'un jeton JWT.
     * Cette méthode utilise le parseur de jetons JWT pour vérifier et extraire
     * les revendications (Claims) d'un jeton JWT donné. Les
     * revendications sont un ensemble de paires clé-valeur qui contiennent
     * des informations sur le jeton et l'utilisateur associé.
     *
     * @param token le jeton JWT dont extraire les revendications
     * @return les revendications du jeton JWT
     */
    public Claims getClaims(String token) {
        return parser.parseClaimsJws(token).getBody();
    }

    /**
     * Extrait l'identifiant de l'utilisateur associé à un jeton JWT.
     * Cette méthode utilise la méthode getClaims() pour extraire
     * les revendications du jeton JWT, puis renvoie la valeur de la revendication
     * "id" sous forme de Long.
     *
     * @param token le jeton JWT dont extraire l'identifiant de l'utilisateur
     * @return l'identifiant de l'utilisateur associé au jeton JWT
     */
    public Long getId(String token) {
        return getClaims(token).get("id", Long.class);
    }

    /**
     * Extrait le nom d'utilisateur associé à un jeton JWT.
     * Cette méthode utilise la méthode getClaims() pour extraire
     * les revendications du jeton JWT, puis renvoie la valeur de la revendication
     * "sub" (pour "subject") sous forme de String.
     *
     * @param token le jeton JWT dont extraire le nom d'utilisateur
     * @return le nom d'utilisateur associé au jeton JWT
     */
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Vérifie si un jeton JWT est valide.
     * Cette méthode utilise la méthode getClaims() pour extraire
     * les revendications du jeton JWT, puis vérifie si la date courante est
     * comprise entre la date d'émission et la date d'expiration du jeton.
     *
     * @param token le jeton JWT à vérifier
     * @return true si le jeton JWT est valide, false sinon
     */
    public boolean isValid(String token) {
        Claims claims = getClaims(token);
        Date now = new Date();
        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }
}