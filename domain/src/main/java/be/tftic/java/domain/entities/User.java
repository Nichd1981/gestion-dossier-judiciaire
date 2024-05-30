package be.tftic.java.domain.entities;

import be.tftic.java.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

/**
 * Classe représentant un utilisateur du système.
 * Cette classe permet de stocker les informations relatives à un utilisateur,
 * telles que son adresse e-mail, son mot de passe et son rôle, ainsi que la
 * personne à laquelle il est associé. Elle implémente l'interface
 * UserDetails de Spring Security pour permettre l'authentification
 * et l'autorisation des utilisateurs.
 *
 */
@Entity
@Table(name = "USER_")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User implements UserDetails {

    /**
     * Identifiant unique de l'utilisateur.
     * Cet identifiant est généré automatiquement par la base de données lorsque
     * l'utilisateur est créé.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Adresse e-mail de l'utilisateur.
     * Cette adresse e-mail est obligatoire et doit être unique pour chaque
     * utilisateur. Elle est utilisée comme nom d'utilisateur pour l'authentification.
     */
    @Column(name = "MAIL", nullable = false, unique = true)
    private String mail;

    /**
     * Mot de passe de l'utilisateur.
     * Ce mot de passe est obligatoire et doit être stocké de manière sécurisée
     * (par exemple : en utilisant un algorithme de hachage tel que BCrypt). Il
     * est utilisé pour l'authentification de l'utilisateur.
     */
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    /**
     * Rôle de l'utilisateur.
     * Ce rôle est obligatoire et est défini pour préciser les autorisations
     * de l'utilisateur (par exemple : "ADMIN", "CITOYEN", "AVOCAT", "AGENT"). Il est
     * utilisé pour l'autorisation de l'utilisateur.
     */
    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Personne à laquelle l'utilisateur est associé.
     * Cette personne est obligatoire et doit être une instance de la classe Personne.
     * Elle permet de lier l'utilisateur à une personne
     * en particulier et de retrouver facilement tous les utilisateurs associés à
     * une personne.
     */
    @ManyToOne
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person person;

    /**
     * Renvoie les autorisations de l'utilisateur.
     * Cette méthode est requise par l'interface UserDetails et
     * doit renvoyer une liste d'objets GrantedAuthority
     * représentant les autorisations de l'utilisateur. Dans cette
     * implémentation, elle renvoie une liste contenant un seul objet
     * SimpleGrantedAuthority représentant le rôle de l'utilisateur.
     *
     * @return les autorisations de l'utilisateur
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.toString()));
    }

    /**
     * Renvoie le mot de passe de l'utilisateur.
     * Cette méthode est requise par l'interface UserDetails et
     * doit renvoyer le mot de passe de l'utilisateur tel qu'il est stocké dans
     * la base de données. Dans cette implémentation, elle renvoie simplement la
     * valeur de l'attribut motDePasse.
     *
     * @return le mot de passe de l'utilisateur
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Renvoie le nom d'utilisateur de l'utilisateur.
     * Cette méthode est requise par l'interface UserDetails et
     * doit renvoyer le nom d'utilisateur de l'utilisateur tel qu'il est utilisé
     * pour l'authentification. Dans cette implémentation, elle renvoie simplement
     * la valeur de l'attribut email.
     *
     * @return le nom d'utilisateur de l'utilisateur
     */
    @Override
    public String getUsername() {
        return this.mail;
    }

    /**
     * Indique si le compte de l'utilisateur est expiré.
     * Cette méthode est requise par l'interface UserDetails et
     * doit renvoyer true si le compte de l'utilisateur est expiré
     * et false sinon. Dans cette implémentation, elle renvoie
     * true si la personne associée à l'utilisateur a une date de
     * décès et false sinon.
     *
     * @return true si le compte de l'utilisateur est expiré, false sinon.
     *
     */
    @Override
    public boolean isAccountNonExpired() {
        return person.getDeathDate() != null;
    }

    /**
     * Indique si le compte de l'utilisateur est verrouillé.
     * Cette méthode est requise par l'interface UserDetails et
     * doit renvoyer true si le compte de l'utilisateur est verrouillé
     * et false sinon. Dans cette implémentation, elle renvoie
     * toujours true.
     *
     * @return true si le compte de l'utilisateur est verrouillé, false sinon.
     *
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indique si les informations d'authentification de l'utilisateur sont expirées.
     * Cette méthode est requise par l'interface UserDetails et
     * doit renvoyer <true si les informations d'authentification de
     * l'utilisateur sont expirées et false sinon. Dans cette
     * implémentation, elle renvoie toujours true.
     *
     * @return true si les informations d'authentification de
     * l'utilisateur sont expirées, false sinon
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indique si l'utilisateur est activé.
     * Cette méthode est requise par l'interface UserDetails et
     * doit renvoyer true si l'utilisateur est activé et false sinon.
     * Dans cette implémentation, elle renvoie toujours true.
     *
     *
     * @return true si l'utilisateur est activé, false sinon
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
