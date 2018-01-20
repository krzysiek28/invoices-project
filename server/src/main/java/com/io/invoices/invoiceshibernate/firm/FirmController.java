package com.io.invoices.invoiceshibernate.firm;

import com.io.invoices.invoiceshibernate.security.AuthorizationFilter;
import com.io.invoices.invoiceshibernate.security.ResourceType;
import com.io.invoices.invoiceshibernate.security.UnauthorizedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/firms")
public class FirmController {

    private final FirmService firmService;
    private final AuthorizationFilter authorizationFilter;

    public FirmController(FirmService firmService, AuthorizationFilter authorizationFilter) {
        this.firmService = firmService;
        this.authorizationFilter = authorizationFilter;
    }

    @RequestMapping("/{ownerId}")
    public List<Firm> getUserFirms(@PathVariable String ownerId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,ownerId, ResourceType.USER);
        return firmService.getFirms(Integer.parseInt(ownerId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}")
    public void addFirm(@RequestBody Firm firm, @PathVariable String ownerId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,ownerId,ResourceType.USER);
        firmService.addFirm(Integer.parseInt(ownerId), firm);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{ownerId}/{firmId}")
    public void updateFirm(@RequestBody Firm firm, @PathVariable String firmId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,firmId, ResourceType.FIRM);
        firmService.updateFirm(Integer.parseInt(firmId), firm);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{ownerId}/{firmId}")
    public void updateFirm(@PathVariable String firmId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,firmId,ResourceType.FIRM);
        firmService.deleteFirm(Integer.parseInt(firmId));
    }
}