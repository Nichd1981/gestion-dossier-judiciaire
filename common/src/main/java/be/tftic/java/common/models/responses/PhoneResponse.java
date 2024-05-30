package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Phone;
import lombok.Builder;
import lombok.Data;

/**
 * Classe de réponse pour les téléphones.
 * Cette classe est annotée avec @Data de Lombok pour générer automatiquement les
 * getters, setters, constructeurs, et méthodes toString(), hashCode(),
 * et equals(). Elle est utilisée pour stocker et transmettre les informations d'un
 * téléphone dans la réponse HTTP.
 *
 */
@Builder
@Data
public class PhoneResponse {

    /**
     * Numéro de téléphone.
     */
    String number;

    /**
     * Libellé du téléphone.
     */
    String label;

    /**
     * Méthode de création d'une instance de TelephoneResponse à partir d'une entité Telephone.
     *
     * @param phone l'entité Telephone à partir de laquelle créer l'instance de TelephoneResponse.
     *
     * @return l'instance de TelephoneResponse créée à partir de l'entité Telephone
     */
    public static PhoneResponse fromEntity(Phone phone) {
        return PhoneResponse.builder()
                .number(phone.getNumber())
                .label(phone.getLabel())
                .build();
    }
}
