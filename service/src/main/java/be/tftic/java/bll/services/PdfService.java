package be.tftic.java.bll.services;

import be.tftic.java.domain.entities.Audition;

public interface PdfService {

    byte[] generatePdfAudition(Audition audition);

}
