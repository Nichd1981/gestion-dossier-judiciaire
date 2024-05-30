package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe de réponse détaillée pour les plaintes.
 *
 * Cette classe est annotée avec @Data de Lombok pour générer automatiquement les
 * getters, setters, constructeurs, et méthodes toString(), hashCode(),
 * et equals(). Elle est utilisée pour stocker et transmettre les informations détaillées
 * d'une plainte dans la réponse HTTP.
 *
 */
@Builder
@Data
public class PlainteDetailResponse {

    /**
     * Numéro de dossier de la plainte.
     */
    String numeroDossier;

    /**
     * Statut de la plainte.
     */
    String statut;

    /**
     * Date de dépôt de la plainte.
     */
    LocalDateTime date;

    /**
     * Informations courtes sur le plaignant.
     */
    PersonneShortResponse plaignant;

    /**
     * Informations courtes sur l'agent traitant la plainte.
     */
    PersonneShortResponse agentTraitant;

    /**
     * Liste des personnes concernées par la plainte.
     */
    List<PersonneShortResponse> personnesConcernees;
    //    Jugement associé à la plainte.
    //    Jugement jugement;
    //    Liste des auditions associées à la plainte.
    //    List<Audition> auditions;
    //    Liste des dépositions associées à la plainte.
    //    List<PlainteDepositionHistorique> depositions;

    /**
     * Méthode de création d'une instance de PlainteDetailResponse à partir d'une entité
     * Plainte.
     *
     * @param plainte l'entité Plainte à partir de laquelle créer l'instance de
     *                PlainteDetailResponse
     * @return l'instance de PlainteDetailResponse créée à partir de l'entité 
     * Plainte
     */
    public static PlainteDetailResponse fromEntity(Plainte plainte) {
        return PlainteDetailResponse.builder()
                .numeroDossier(plainte.getNumeroDossier())
                .statut(plainte.getStatut().toString())
                .date(plainte.getDatePlainte())
                .plaignant(PersonneShortResponse.fromEntity(plainte.getPlaignant()))
                .agentTraitant(PersonneShortResponse.fromEntity(plainte.getAgentTraitant()))
                .personnesConcernees(plainte.getPersonnesConcernees().stream().map(PersonneShortResponse::fromEntity).toList())
                //                .jugement(plainte.getJugement())
                //                .auditions(plainte.getAuditions())
                //                .depositions(plainte.getDepositions())
                .build();
    }
}