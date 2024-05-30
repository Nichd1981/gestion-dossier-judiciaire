package be.tftic.java.common.models.requests;

import be.tftic.java.domain.entities.Utilisateur;

/**
 * Classe de requête de connexion.
 * Cette classe est utilisée pour stocker les informations nécessaires à la connexion d'un utilisateur,
 * telles que l'adresse e-mail et le mot de passe.
 *
 */
public record LoginRequest(

        /**
         * L'adresse e-mail de l'utilisateur.
         */
        String email,

        /**
         * Le mot de passe de l'utilisateur.
         */
        String motDePasse

) {

    /**
     * Méthode pour convertir la requête de connexion en une entité d'utilisateur.
     *
     * @return l'entité d'utilisateur créée à partir de la requête de connexion
     */
    public Utilisateur toEntity() {
        return Utilisateur.builder()
                .email(this.email)
                .motDePasse(this.motDePasse)
                .build();
    }
}
