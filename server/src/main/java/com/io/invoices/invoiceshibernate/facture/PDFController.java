package com.io.invoices.invoiceshibernate.facture;

import com.io.invoices.invoiceshibernate.pdfCreator.PdfCreator;
import com.io.invoices.invoiceshibernate.security.AuthorizationFilter;
import com.io.invoices.invoiceshibernate.security.ResourceType;
import com.io.invoices.invoiceshibernate.security.UnauthorizedException;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.io.invoices.invoiceshibernate.security.SecurityUtils.CLIENT_ADDRESS;

@RestController
@RequestMapping("/pdf")
public class PDFController {

    private final FactureService factureService;
    private final AuthorizationFilter authorizationFilter;

    public PDFController(FactureService factureService, AuthorizationFilter authorizationFilter) {
        this.factureService = factureService;
        this.authorizationFilter = authorizationFilter;
    }

    @CrossOrigin(origins = CLIENT_ADDRESS)
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public void createPdf(@PathVariable String id, HttpServletResponse response, @RequestHeader("Authorization") String token) throws IOException, DocumentException, com.itextpdf.text.DocumentException, UnauthorizedException {

        authorizationFilter.isAuthorizedTo(token, id, ResourceType.FACTURE);
        PdfCreator pdfCreator = new PdfCreator(factureService.getFacture(Integer.parseInt(id)));
        pdfCreator.createPdf();

        File initialFile = new File("invoice.pdf");
        InputStream input = new FileInputStream(initialFile);
        try {
            // get your file as InputStream
            InputStream is = input;
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {

            throw new RuntimeException("IOError writing file to output stream");
        }
    }
}
