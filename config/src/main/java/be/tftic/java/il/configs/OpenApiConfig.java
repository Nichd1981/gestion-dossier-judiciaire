package be.tftic.java.il.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuration pour OpenAPI. Elle configure le schéma de sécurité pour
 * l'authentification via un jeton JWT (JSON Web Token) en utilisant le schéma "bearer".
 *
 *Cette classe définit un bean OpenAPI qui ajoute un élément de sécurité et
 * configure les schémas de sécurité pour l'authentification via un jeton JWT.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configure et personnalise l'instance OpenAPI avec un schéma de sécurité pour
     * l'authentification "bearer" en utilisant des jetons JWT.
     *
     * @return une instance configurée de {@link OpenAPI}
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

