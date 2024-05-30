package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Telephone;
import lombok.Builder;
import lombok.Data;

/**
 * Classe de réponse pour les téléphones.
 *
 * Cette classe est annotée avec @Data de Lombok pour générer automatiquement les
 * getters, setters, constructeurs, et méthodes toString(), hashCode(),
 * et equals(). Elle est utilisée pour stocker et transmettre les informations d'un
 * téléphone dans la réponse HTTP.
 *
 */
@Builder
@Data
public class TelephoneResponse {

    /**
     * Numéro de téléphone.
     */
    String numero;

    /**
     * Libellé du téléphone.
     */
    String libelle;

    /**
     * Méthode de création d'une instance de TelephoneResponse à partir d'une entité Telephone.
     *
     * @param telephone l'entité Telephone à partir de laquelle créer l'instance de TelephoneResponse.
     *
     * @return l'instance de TelephoneResponse créée à partir de l'entité Telephone
     */
    public static TelephoneResponse fromEntity(Telephone telephone) {
        return TelephoneResponse.builder()
                .numero(telephone.getNumero())
                .libelle(telephone.getLibelle())
                .build();
    }
}
