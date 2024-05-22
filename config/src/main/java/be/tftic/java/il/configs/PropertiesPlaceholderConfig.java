package be.tftic.java.il.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

/**
 * Configuration class for the application.
 * It will replace property placeholders (application.yml) with the ones in the .env file.
 * This class is annotated with @Configuration to indicate that it is a source of bean definitions.
 * The AppConfig class is not instantiable, attempting to do so will result in an IllegalStateException.
 */
@Configuration
public class PropertiesPlaceholderConfig {

    /**
     * Bean definition for PropertySourcesPlaceholderConfigurer.
     * This bean allows the application to use placeholders in property files.
     * The placeholders are replaced with the actual values from the .env file.
     *
     * @return a PropertySourcesPlaceholderConfigurer with the location of the .env file set.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();

        configurer.setLocation(new FileSystemResource(".env"));

        return configurer;
    }
}
