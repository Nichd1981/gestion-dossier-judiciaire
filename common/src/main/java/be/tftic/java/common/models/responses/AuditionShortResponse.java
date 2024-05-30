package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Audition;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe représentant une version abrégée d'une audition.
 *Cette classe Java est un POJO (Plain Old Java Object) qui représente une version abrégée d'une audition.
 * Elle est annotée avec @Builder et @Data de la bibliothèque Lombok pour générer automatiquement le code pour le constructeur,
 * les getters et les setters, ainsi que d'autres méthodes utilitaires.
 *
 */
@Builder
@Data
public class AuditionShortResponse {

	/**
	 * La date et l'heure de l'audition.
	 */
	LocalDateTime date;

	/**
	 * Le compte rendu de l'audition.
	 */
	String audition;

	/**
	 * Le numéro de la salle où l'audition a eu lieu.
	 */
	int numeroSalle;

	/**
	 * La personne convoquée à l'audition.
	 */
	PersonneShortResponse convoque;

	/**
	 * L'agent qui a mené l'audition.
	 */
	PersonneShortResponse agentTraitant;

	/**
	 * L'avocat de la personne convoquée, s'il y en a un.
	 */
	PersonneShortResponse avocat;

	/**
	 * La plainte associée à l'audition.
	 */
	PlainteShortResponse plainte;

	/**
	 * Méthode statique pour construire une instance de {@link AuditionShortResponse} à partir d'un objet {@link Audition}.
	 *
	 * @param audition l'objet Audition à mapper vers un objet AuditionShortResponse
	 * @return l'objet AuditionShortResponse construit à partir de l'objet Audition
	 */
	public static AuditionShortResponse fromEntity(Audition audition) {
		return AuditionShortResponse.builder()
				.date(audition.getDateAudition())
				.audition(audition.getDepositionAudition())
				.numeroSalle(audition.getNumeroSalleAudition())
				.convoque(PersonneShortResponse.fromEntity(audition.getConvoque()))
				.agentTraitant(PersonneShortResponse.fromEntity(audition.getAgentTraitant()))
				.avocat(PersonneShortResponse.fromEntity(audition.getAvocat()))
				.plainte(PlainteShortResponse.fromEntity(audition.getPlainte()))
				.build();
	}
}