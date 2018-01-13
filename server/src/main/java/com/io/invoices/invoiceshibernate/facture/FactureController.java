package com.io.invoices.invoiceshibernate.facture;

import com.io.invoices.invoiceshibernate.user.ApplicationUser;
import com.io.invoices.invoiceshibernate.user.ApplicationUserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.io.invoices.invoiceshibernate.security.SecurityUtils.SECRET;
import static com.io.invoices.invoiceshibernate.security.SecurityUtils.TOKEN_PREFIX;

@RestController
@RequestMapping("/factures")
public class FactureController {

    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @RequestMapping("/{firmId}")
    public List<Facture> getAllFactures(@PathVariable String firmId) {
        return factureService.getAllFactures(Integer.parseInt(firmId));
    }

    @RequestMapping("/{firmId}/{factureId}")
    public Facture getFacture(@PathVariable String factureId) {
        return factureService.getFacture(Integer.parseInt(factureId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{firmId}")
    public void addFacture(@RequestHeader("Authorization") String token, @RequestBody Facture facture, @PathVariable String firmId) {

        facture.setIssuer(
                factureService.getIssuer(token)
        );
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
}
