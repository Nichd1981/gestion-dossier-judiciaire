package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.exceptions.ApplicationException;
import be.tftic.java.bll.services.AuditionService;
import be.tftic.java.bll.services.ComplaintService;
import be.tftic.java.bll.services.PdfService;
import be.tftic.java.domain.entities.Audition;
import be.tftic.java.domain.entities.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {

	private final TemplateEngine templateEngine;
	private final AuditionService auditionService;
	private final ComplaintService complaintService;

	public byte[] generatePdfAudition(Long auditionId) {

		Audition audition = auditionService.findById(auditionId);

		Context context = new Context();
		context.setVariable("audition", audition);

		String htmlContent = templateEngine.process("audition/audition", context);

		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();
			renderer.createPDF(outputStream);
			return outputStream.toByteArray();
		} catch (Exception e) {
			throw new ApplicationException("Error while generating PDF", e);
		}
	}

	public byte[] generatePdfComplaint(Long complaintId) {

		Complaint complaint = complaintService.findComplaintById(complaintId);

		Context context = new Context();
		context.setVariable("complaint", complaint);

		String htmlContent = templateEngine.process("complaint/complaint", context);

		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();
			renderer.createPDF(outputStream);
			return outputStream.toByteArray();
		} catch (Exception e) {
			throw new ApplicationException("Error while generating PDF", e);
		}
	}
}
