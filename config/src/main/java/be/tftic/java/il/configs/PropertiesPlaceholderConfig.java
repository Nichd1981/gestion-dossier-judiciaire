package be.tftic.java.il.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

/**
 * Classe de configuration pour la gestion des propriétés externes.
 * Cette classe configure un bean {@link PropertySourcesPlaceholderConfigurer}
 * pour charger les propriétés à partir d'un fichier de configuration externe.
 *
 * Cette classe définit un bean statique {@link PropertySourcesPlaceholderConfigurer}
 * qui facilite le chargement des propriétés externes à partir d'un fichier de configuration.
 * Le fichier de configuration spécifié est utilisé pour stocker les différentes propriétés
 * de configuration nécessaires à l'application.
 */
@Configuration
public class PropertiesPlaceholderConfig {

    /**
     * Configure le {@link PropertySourcesPlaceholderConfigurer} pour charger les propriétés
     * à partir du fichier de configuration externe spécifié.
     *
     * @return une instance configurée de {@link PropertySourcesPlaceholderConfigurer}
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();

        // Spécifiez le chemin vers votre fichier de configuration externe ici
        configurer.setLocation(new FileSystemResource("chemin/vers/votre/fichier/de/configuration"));

        return configurer;
    }
}


