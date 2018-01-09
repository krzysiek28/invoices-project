package web.mvc.controllers;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.mvc.service.ClientService;
import web.mvc.service.UserAuthenticationService;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

@Controller
public class ClientController {

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/clients")
    public String clientsPage(HttpServletRequest request, ModelMap modelMap) {
        try {
            modelMap.addAttribute("authservice", userAuthenticationService);
            modelMap.addAttribute("clients", clientService.getFirmClients());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "clients";
    }

    @RequestMapping(value = "/clients/addclient", method = RequestMethod.POST)
    public String addClient(@RequestParam("name") String name,
                            @RequestParam("additionalData") String additionalData,
                            HttpServletRequest request,
                            ModelMap modelMap) throws URISyntaxException, JSONException {
        clientService.addClient(name, additionalData);
        return "redirect:/clients";
    }

    @RequestMapping(value = "/clients/deleteclient/{id}", method = RequestMethod.GET)
    public String deleteClient(@PathVariable("id") String id) throws URISyntaxException {
        clientService.deleteClient(Integer.parseInt(id));
        return "redirect:/clients";
    }
}