package be.java.gestiondossierjudiciare.api.forms;

import be.java.gestiondossierjudiciare.domain.entities.Connexion;

public record LoginForm(
        String email,
        String motDePasse
) {
    public Connexion toEntity() {
        return Connexion.builder()
                .email(this.email)
                .motDePasse(this.motDePasse)
                .build();
    }
}
