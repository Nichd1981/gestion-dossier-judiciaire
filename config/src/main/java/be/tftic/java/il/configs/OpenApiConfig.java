package be.tftic.java.il.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuration de l'API ouverte de l'application.
 * Cette classe permet de configurer l'API ouverte de l'application à l'aide de la bibliothèque
 * OpenAPI de Swagger. Elle est annotée avec @Configuration pour indiquer à Spring de
 * créer et gérer une instance unique de cette classe.
 *
 */
@Configuration
public class OpenApiConfig {

    /**
     * Crée une configuration personnalisée de l'API ouverte de l'application.
     * Cette méthode est annotée avec @Bean pour indiquer à Spring de créer et gérer
     * une instance unique de cette classe. Elle utilise la classe OpenAPI de Swagger
     * pour créer une configuration personnalisée de l'API ouverte de l'application. Dans cette
     * implémentation, elle ajoute un schéma de sécurité de type "bearer" pour l'authentification JWT,
     * et configure ce schéma de sécurité pour qu'il soit utilisé pour toutes les requêtes HTTP de
     * l'API.
     *
     * @return la configuration personnalisée de l'API ouverte créée
     */
    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
