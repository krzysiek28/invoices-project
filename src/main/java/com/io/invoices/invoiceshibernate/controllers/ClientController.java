package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.client.Client;
import com.io.invoices.invoiceshibernate.client.ClientService;
import com.io.invoices.invoiceshibernate.user.Usery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/addclient", method = RequestMethod.POST)
    public String addClient(@Valid @ModelAttribute("name") String name,
                             @ModelAttribute("additionalData") String additionalData,
                             @ModelAttribute("owner") String owner,
                             BindingResult result,
                             ModelMap modelMap){
        if (!result.hasErrors()){
            clientService.addClient(owner, new Client(name, additionalData));
            return "success";
        } else {
            return "error";
        }
    }
    //

    @RequestMapping(value = "/{ownerId}", method = RequestMethod.POST)
    public void addClient(@RequestBody Client client, @PathVariable String ownerId) {
        clientService.addClient(ownerId, client);
    }

    @RequestMapping("/{ownerId}")
    public List<Client> getAllClients(@PathVariable String ownerId) {
        return clientService.getAllClients(ownerId);
    }

    @RequestMapping(value = "/{ownerId}/{clientId}", method = RequestMethod.PUT)
    public void updateClient(@RequestBody Client client, @PathVariable String clientId) {
        clientService.updateClient(clientId, client);
    }

//    @RequestMapping(value = "/{ownerId}/{clientId}", method = RequestMethod.DELETE)
//    public void deleteClient(@PathVariable String clientId) {
//        clientService.deleteClient(clientId);
//    }

    @RequestMapping(value = "/deleteclient/{clientId}", method = RequestMethod.GET)
    public String deleteClient(@PathVariable("clientId") String clientId) {
        if(clientId!=null){
            clientService.deleteClient(clientId);
            return "success";
        }else
            return "error";
    }

}
