package be.tftic.java.il.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

/**
 * Classe de configuration des propriétés de l'application.
 *
 * Cette classe permet de configurer les propriétés de l'application à partir d'un
 * fichier de propriétés externe, en utilisant la classe PropertySourcesPlaceholderConfigurer
 * de Spring. Elle est annotée avec @Configuration pour indiquer à Spring de
 * créer et gérer une instance unique de cette classe.
 *
 */
@Configuration
public class PropertiesPlaceholderConfig {

    /**
     * Crée un gestionnaire de propriétés pour l'application.
     *
     * Cette méthode est annotée avec @Bean et static pour indiquer à
     * Spring de créer et gérer une instance unique de cette classe, et de la créer avant les
     * autres beans de la classe PropertiesPlaceholderConfig. Elle utilise la classe
     * PropertySourcesPlaceholderConfigurer de Spring pour créer un gestionnaire de
     * propriétés à partir d'un fichier de propriétés externe. Dans cette implémentation, elle
     * utilise la classe FileSystemResource de Spring pour charger le fichier de
     * propriétés à partir du système de fichiers, en utilisant le chemin d'accès ".env".
     *
     * @return le gestionnaire de propriétés créé
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();

        configurer.setLocation(new FileSystemResource(".env"));

        return configurer;
    }
}
