package be.java.gestiondossierjudiciare.api.forms;

import be.java.gestiondossierjudiciare.domain.enums.Statut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlainteFilter {

    private String numeroDossier;
    private LocalDate dateLowerBound;
    private LocalDate dateUpperBound;
    private String statut;
    private String type;

}
