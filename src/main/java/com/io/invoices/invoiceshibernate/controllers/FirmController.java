package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.firm.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/firms")
public class FirmController {

    @Autowired
    private FirmService firmService;

    @RequestMapping ("/firms")
    public List<Firm> getFirms(){
        return firmService.getFirms();
    }

    @RequestMapping(method = RequestMethod.POST, value="")
    public void addFirm(@RequestBody Firm firm) {
        firmService.addFirm(firm);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/{firmId}")
    public void updateFirm(@RequestBody Firm firm, @PathVariable String firmId) {
        firmService.updateFirm(firmId, firm);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{firmId}")
    public void updateFirm(@PathVariable String firmId) {
        firmService.deleteFirm(firmId);
    }
}