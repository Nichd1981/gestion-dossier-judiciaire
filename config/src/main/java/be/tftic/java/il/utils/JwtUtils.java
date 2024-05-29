package be.tftic.java.il.utils;

import be.tftic.java.domain.entities.Utilisateur;
import be.tftic.java.il.configs.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Classe utilitaire pour la manipulation des jetons JWT (JSON Web Token).
 * Cette classe permet de générer des jetons JWT, de récupérer les réclamations
 * (claims) à partir d'un jeton, et de vérifier la validité d'un jeton.
 *
 *Cette classe est annotée avec {@link Component} pour être détectée
 * automatiquement comme un composant Spring et pour bénéficier de l'injection
 * automatique des dépendances.
 */
@Component
public class JwtUtils {

    private final JwtConfig config;
    private final JwtBuilder builder;
    private final JwtParser parser;

    /**
     * Constructeur de JwtUtils.
     *
     * @param config la configuration JWT
     */
    public JwtUtils(JwtConfig config) {
        this.config = config;
        this.builder = Jwts.builder().signWith(config.getSecretKey());
        this.parser = Jwts.parserBuilder().setSigningKey(config.getSecretKey()).build();
    }

    /**
     * Génère un jeton JWT pour l'utilisateur spécifié.
     *
     * @param c l'utilisateur pour lequel générer le jeton
     * @return le jeton JWT généré
     */
    public String generateToken(Utilisateur c) {
        return builder
                .setSubject(c.getUsername())
                .claim("id", c.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + config.getExpireAt() * 1000L))
                .compact();
    }

    /**
     * Récupère les réclamations (claims) à partir d'un jeton JWT.
     *
     * @param token le jeton JWT à décoder
     * @return les réclamations (claims) extraites du jeton
     */
    public Claims getClaims(String token) {
        return parser.parseClaimsJws(token).getBody();
    }

    /**
     * Récupère l'identifiant de l'utilisateur à partir d'un jeton JWT.
     *
     * @param token le jeton JWT à décoder
     * @return l'identifiant de l'utilisateur extrait du jeton
     */
    public Long getId(String token) {
        return getClaims(token).get("id", Long.class);
    }

    /**
     * Récupère le nom d'utilisateur à partir d'un jeton JWT.
     *
     * @param token le jeton JWT à décoder
     * @return le nom d'utilisateur extrait du jeton
     */
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Vérifie si un jeton JWT est valide.
     *
     * @param token le jeton JWT à vérifier
     * @return true si le jeton est valide, sinon false
     */
    public boolean isValid(String token) {
        Claims claims = getClaims(token);
        Date now = new Date();
        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }
}

