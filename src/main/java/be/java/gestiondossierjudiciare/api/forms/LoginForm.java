package be.java.gestiondossierjudiciare.api.forms;

import be.java.gestiondossierjudiciare.domain.entities.Utilisateur;

public record LoginForm(
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
