package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.facture.Facture;
import com.io.invoices.invoiceshibernate.facture.FactureService;
import com.io.invoices.invoiceshibernate.pdfCreator.PdfCreator;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/factures")
public class FactureController {
    @Autowired
    FactureService factureService;

    @RequestMapping("/{ownerId}/factures")
    public List<Facture> getAllFactures(@PathVariable String ownerId) {
        return factureService.getAllFactures(ownerId);
    }

    @RequestMapping("/{id}")
    public Facture getFacture(@PathVariable String id) {return factureService.getFacture(id);}

    @RequestMapping("/createPdf/{id}")
    public void createPdf(@PathVariable String id) throws IOException, DocumentException, com.itextpdf.text.DocumentException {

        PdfCreator pdfCreator = new PdfCreator(getFacture(id));
        pdfCreator.createPdf();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/send")
    public ResponseEntity<byte[]> getPDF(@RequestBody String json) throws IOException {
        File initialFile = new File("src/main/resources/sample.txt");
        InputStream input = new FileInputStream(initialFile);
        byte[] contents = org.apache.commons.io.IOUtils.toByteArray(input);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
        return response;
    }
/*    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}/factures")
    public void addFacture(@PathVariable String ownerId, @RequestBody Facture facture) {
        factureService.addFacture(ownerId, facture);
    }*/


}
