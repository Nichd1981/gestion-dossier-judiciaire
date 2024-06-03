package be.tftic.java.bll.services.impls;

import be.tftic.java.bll.services.AuditionService;
import be.tftic.java.bll.services.PDFGenerationService;
import be.tftic.java.common.models.responses.AuditionShortResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class PDFGenerationServiceImpl implements PDFGenerationService {

    private final TemplateEngine templateEngine;
    private final AuditionService auditionService;

    public byte[] generatePdf(Long id) {
        Context context = new Context();
        AuditionShortResponse audition = auditionService.findById(id);
        context.setVariable("audition", audition);

        String htmlContent = templateEngine.process("template", context);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error while generating PDF", e);
        }
    }

}
