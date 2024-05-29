package be.tftic.java.common.models.responses;

import be.tftic.java.domain.entities.Audition;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class AuditionShortResponse {

	LocalDateTime date;
	String audition;
	int numeroSalle;
	PersonneShortResponse convoque;
	PersonneShortResponse agentTraitant;
	PersonneShortResponse avocat;
	PlainteShortResponse plainte;

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
