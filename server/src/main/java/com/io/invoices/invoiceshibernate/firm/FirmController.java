package com.io.invoices.invoiceshibernate.firm;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/firms")
public class FirmController {

    private final FirmService firmService;

    public FirmController(FirmService firmService) {
        this.firmService = firmService;
    }

    @RequestMapping("/{ownerId}")
    public List<Firm> getUserFirms(@PathVariable String ownerId) {
        return firmService.getFirms(Integer.parseInt(ownerId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}")
    public void addFirm(@RequestBody Firm firm, @PathVariable String ownerId) {
        firmService.addFirm(Integer.parseInt(ownerId), firm);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{ownerId}/{firmId}")
    public void updateFirm(@RequestBody Firm firm, @PathVariable String firmId) {
        firmService.updateFirm(Integer.parseInt(firmId), firm);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{ownerId}/{firmId}")
    public void updateFirm(@PathVariable String firmId) {
        firmService.deleteFirm(Integer.parseInt(firmId));
    }
}