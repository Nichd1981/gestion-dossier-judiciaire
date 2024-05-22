package be.tftic.java.common.models.requests;

import be.tftic.java.domain.entities.Utilisateur;

public record LoginRequest(
        String email,
        String motDePasse
) {
    public Utilisateur toEntity() {
        return Utilisateur.builder()
                .email(this.email)
                .motDePasse(this.motDePasse)
                .build();
    }
}
