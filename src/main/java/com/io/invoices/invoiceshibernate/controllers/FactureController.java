package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.facture.Facture;
import com.io.invoices.invoiceshibernate.facture.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}/factures")
    public void addFacture(@PathVariable String ownerId, @RequestBody Facture facture) {
        factureService.addFacture(ownerId, facture);
    }


}
