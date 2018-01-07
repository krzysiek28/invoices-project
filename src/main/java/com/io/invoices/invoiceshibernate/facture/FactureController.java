package com.io.invoices.invoiceshibernate.facture;

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

    @RequestMapping("/{firmId}")
    public List<Facture> getAllFactures(@PathVariable String firmId) {
        return factureService.getAllFactures(Integer.parseInt(firmId));
    }

    @RequestMapping("/{firmId}/{factureId}")
    public Facture getFacture(@PathVariable String factureId) {return factureService.getFacture(Integer.parseInt(factureId));}

    @RequestMapping(method = RequestMethod.POST, value = "/{firmId}")
    public void addFacture(@RequestBody Facture facture, @PathVariable String firmId) {
        factureService.addFacture(Integer.parseInt(firmId), facture);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{firmId}/{factureId}")
    public void deleteFacture(@PathVariable String firmId, @PathVariable String factureId) {
        factureService.deleteFacture(Integer.parseInt(factureId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{firmId}/{factureId}")
    public void updateFacture(@PathVariable String firmId, @PathVariable String factureId, @RequestBody Facture facture) {
        factureService.updateFacture(Integer.parseInt(factureId), facture);
    }


    @RequestMapping("/createPdf/{id}")
    public void createPdf(@PathVariable String id, HttpServletResponse response) throws IOException, DocumentException, com.itextpdf.text.DocumentException {

        PdfCreator pdfCreator = new PdfCreator(getFacture(id));
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
/*
    @RequestMapping( "/sendPdf")
    public void getPDF(HttpServletResponse response) throws IOException {
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
    */
/*    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}/factures")
    public void addFacture(@PathVariable String ownerId, @RequestBody Facture facture) {
        factureService.addFacture(ownerId, facture);
    }*/


}
