package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.facture.Facture;
import com.io.invoices.invoiceshibernate.facture.FactureService;
import com.io.invoices.invoiceshibernate.pdfCreator.PdfCreator;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
/*    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}/factures")
    public void addFacture(@PathVariable String ownerId, @RequestBody Facture facture) {
        factureService.addFacture(ownerId, facture);
    }*/


}
