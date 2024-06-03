package be.tftic.java.bll.services.impls;

import be.tftic.java.domain.entities.Audition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl {

	private final TemplateEngine templateEngine;

	public byte[] generatePdf(Audition audition) {
		Context context = new Context();
		context.setVariable("audition", audition);

		String htmlContent = templateEngine.process("PdfHtml.html", context);

		try (OutputStream outputStream = new ByteArrayOutputStream()) {
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();
			renderer.createPDF(outputStream);
			return ((ByteArrayOutputStream) outputStream).toByteArray();
		} catch (Exception e) {
			throw new RuntimeException("Error while generating PDF", e);
		}
	}
}
