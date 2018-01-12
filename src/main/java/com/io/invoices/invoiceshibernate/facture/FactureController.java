package com.io.invoices.invoiceshibernate.facture;

import com.io.invoices.invoiceshibernate.facture.Facture;
import com.io.invoices.invoiceshibernate.facture.FactureService;
import com.io.invoices.invoiceshibernate.pdfCreator.PdfCreator;
import com.io.invoices.invoiceshibernate.user.ApplicationUser;
import com.io.invoices.invoiceshibernate.user.ApplicationUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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

import static com.io.invoices.invoiceshibernate.security.SecurityUtils.SECRET;
import static com.io.invoices.invoiceshibernate.security.SecurityUtils.TOKEN_PREFIX;

@RestController
@RequestMapping("/factures")
public class FactureController {
    @Autowired
    FactureService factureService;

    @Autowired
    ApplicationUserRepository  applicationUserRepository;
    @RequestMapping("/{firmId}")
    public List<Facture> getAllFactures(@PathVariable String firmId) {
        return factureService.getAllFactures(Integer.parseInt(firmId));
    }

    @RequestMapping("/{firmId}/{factureId}")
    public Facture getFacture(@PathVariable String factureId) {return factureService.getFacture(Integer.parseInt(factureId));}

    @RequestMapping(method = RequestMethod.POST, value = "/{firmId}")
    public void addFacture(@RequestHeader("Authorization") String token, @RequestBody Facture facture, @PathVariable String firmId) {
        String username = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        facture.setIssuer(user.getPersonalData());
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
