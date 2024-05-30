package be.tftic.java.common.models.requests.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditionFilterRequest {

	private LocalDate dateLowerBound;
	private LocalDate dateUpperBound;
	private String keyword;

}
