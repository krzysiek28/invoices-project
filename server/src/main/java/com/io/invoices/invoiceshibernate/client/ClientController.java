package com.io.invoices.invoiceshibernate.client;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/{firmId}", method = RequestMethod.POST)
    public void addClient(@RequestBody Client client, @PathVariable String firmId) {
        clientService.addClient(Integer.parseInt(firmId), client);
    }

    @RequestMapping("/{firmId}")
    public List<Client> getAllClients(@PathVariable String firmId) {
        return clientService.getAllClients(Integer.parseInt(firmId));
    }

    @RequestMapping(value = "/{firmId}/{clientId}", method = RequestMethod.PUT)
    public void updateClient(@RequestBody Client client, @PathVariable String clientId) {
        clientService.updateClient(Integer.parseInt(clientId), client);
    }

    @RequestMapping(value = "/{firmId}/{clientId}", method = RequestMethod.DELETE)
    public void deleteClient(@PathVariable String clientId) {
        clientService.deleteClient(Integer.parseInt(clientId));
    }

}
