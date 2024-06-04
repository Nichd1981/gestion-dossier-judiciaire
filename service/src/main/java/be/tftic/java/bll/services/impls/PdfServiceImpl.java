package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.exceptions.ApplicationException;
import be.tftic.java.bll.services.AuditionService;
import be.tftic.java.bll.services.PdfService;
import be.tftic.java.domain.entities.Audition;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {

	private final TemplateEngine templateEngine;
	private final AuditionService auditionService;

	public byte[] generatePdfAudition(Long auditionId) {

		Audition audition = auditionService.findById(auditionId);

		Context context = new Context();
		context.setVariable("audition", audition);

		String htmlContent = templateEngine.process("audition/audition", context);

		try (OutputStream outputStream = new ByteArrayOutputStream()) {
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();
			renderer.createPDF(outputStream);
			return ((ByteArrayOutputStream) outputStream).toByteArray();
		} catch (Exception e) {
			throw new ApplicationException("Error while generating PDF", e);
		}
	}
}
