package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.client.Client;
import com.io.invoices.invoiceshibernate.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/{ownerId}/users", method = RequestMethod.POST)
    public void addClient(@RequestBody Client client, @PathVariable String ownerId) {
        clientService.addClient(ownerId, client);
    }

    @RequestMapping("/{ownerId}/users")
    public List<Client> getAllClients(@PathVariable String ownerId) {
        return clientService.getAllClients(ownerId);
    }
}
