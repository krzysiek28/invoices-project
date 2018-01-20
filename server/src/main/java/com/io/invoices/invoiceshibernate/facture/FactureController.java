package com.io.invoices.invoiceshibernate.facture;

import com.io.invoices.invoiceshibernate.security.AuthorizationFilter;
import com.io.invoices.invoiceshibernate.security.ResourceType;
import com.io.invoices.invoiceshibernate.security.UnauthorizedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factures")
public class FactureController {

    private final FactureService factureService;
    private final AuthorizationFilter authorizationFilter;

    public FactureController(FactureService factureService, AuthorizationFilter authorizationFilter) {
        this.factureService = factureService;
        this.authorizationFilter = authorizationFilter;
    }

    @RequestMapping("/{firmId}")
    public List<Facture> getAllFactures(@PathVariable String firmId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,firmId, ResourceType.FIRM);
        return factureService.getAllFactures(Integer.parseInt(firmId));
    }

    @RequestMapping("/{firmId}/{factureId}")
    public Facture getFacture(@PathVariable String factureId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,factureId,ResourceType.FACTURE);
        return factureService.getFacture(Integer.parseInt(factureId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{firmId}")
    public void addFacture(@RequestHeader("Authorization") String token, @RequestBody Facture facture, @PathVariable String firmId) throws UnauthorizedException {

        authorizationFilter.isAuthorizedTo(token,firmId,ResourceType.FIRM);
        facture.setIssuer(
                factureService.getIssuer(token)
        );
        factureService.addFacture(Integer.parseInt(firmId), facture);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{firmId}/{factureId}")
    public void deleteFacture(@PathVariable String firmId, @PathVariable String factureId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,factureId,ResourceType.FACTURE);
        factureService.deleteFacture(Integer.parseInt(factureId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{firmId}/{factureId}")
    public void updateFacture(@PathVariable String firmId, @PathVariable String factureId, @RequestBody Facture facture, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,factureId,ResourceType.FACTURE);
        factureService.updateFacture(Integer.parseInt(factureId), facture);
    }
}
