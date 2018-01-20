package com.io.invoices.invoiceshibernate.client;

import com.io.invoices.invoiceshibernate.security.AuthorizationFilter;
import com.io.invoices.invoiceshibernate.security.ResourceType;
import com.io.invoices.invoiceshibernate.security.UnauthorizedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final AuthorizationFilter authorizationFilter;

    public ClientController(ClientService clientService, AuthorizationFilter authorizationFilter) {
        this.clientService = clientService;
        this.authorizationFilter = authorizationFilter;
    }

    @RequestMapping(value = "/{firmId}", method = RequestMethod.POST)
    public void addClient(@RequestBody Client client, @PathVariable String firmId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,firmId, ResourceType.FIRM);
        clientService.addClient(Integer.parseInt(firmId), client);
    }

    @RequestMapping("/{firmId}")
    public List<Client> getAllClients(@PathVariable String firmId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,firmId,ResourceType.FIRM);
        return clientService.getAllClients(Integer.parseInt(firmId));
    }

    @RequestMapping(value = "/{firmId}/{clientId}", method = RequestMethod.PUT)
    public void updateClient(@RequestBody Client client, @PathVariable String clientId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,clientId,ResourceType.CLIENT);
        clientService.updateClient(Integer.parseInt(clientId), client);
    }

    @RequestMapping(value = "/{firmId}/{clientId}", method = RequestMethod.DELETE)
    public void deleteClient(@PathVariable String clientId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,clientId,ResourceType.CLIENT);
        clientService.deleteClient(Integer.parseInt(clientId));
    }

}
