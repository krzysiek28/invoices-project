package com.io.invoices.invoiceshibernate.client;

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

//    @RequestMapping(value = "/addclient", method = RequestMethod.POST)
//    public String addClient(@Valid @ModelAttribute("name") String name,
//                             @ModelAttribute("additionalData") String additionalData,
//                             @ModelAttribute("owner") String owner,
//                             BindingResult result,
//                             ModelMap modelMap){
//        if (!result.hasErrors()){
//            clientService.addClient(owner, new Client(name, additionalData));
//            return "success";
//        } else {
//            return "error";
//        }
//    }
//    //

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

//    @RequestMapping(value = "/{ownerId}/{clientId}", method = RequestMethod.DELETE)
//    public void deleteClient(@PathVariable String clientId) {
//        clientService.deleteClient(clientId);
//    }

    @RequestMapping(value = "/{firmId}/{clientId}", method = RequestMethod.DELETE)
    public void deleteClient(@PathVariable String clientId) {
        clientService.deleteClient(Integer.parseInt(clientId));
    }

}
