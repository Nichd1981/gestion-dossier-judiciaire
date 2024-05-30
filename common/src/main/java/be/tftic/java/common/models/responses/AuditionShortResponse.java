package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Audition;
import lombok.Builder;
import lombok.Data;

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
	String roomNumber;
    /**
     * La personne convoquée à l'audition.
     */
	PersonShortResponse citizen;
    /**
     * L'agent qui a mené l'audition.
     */
	PersonShortResponse agent;
    /**
     * L'avocat de la personne convoquée, s'il y en a un.
     */
	PersonShortResponse lawyer;
    /**
     * La plainte associée à l'audition.
     */
	ComplaintShortResponse complaint;

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
				.roomNumber(audition.getRoomNumberAudition())
				.citizen(PersonShortResponse.fromEntity(audition.getCitizen()))
				.agent(PersonShortResponse.fromEntity(audition.getAgent()))
				.lawyer(PersonShortResponse.fromEntity(audition.getLawyer()))
				.complaint(ComplaintShortResponse.fromEntity(audition.getComplaint()))
				.build();
	}
}
