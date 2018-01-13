package web.mvc.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import web.mvc.domain.Client;
import web.mvc.domain.User;
import web.mvc.service.ClientService;
import web.mvc.service.UserAuthenticationService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class ClientController {

    UserAuthenticationService userAuthenticationService;
    ClientService clientService;

    //Zależności są wstrzykiwane przez konstruktor, a nie poprzez wstrzykiwanie pojedyńczych pól przy użyciu @Autowired
    //Dobrym pomysłem będzie przejście na taki model, jest to bardziej poprawne podejście i umożliwia (chyba) też
    //załadowanie własnych obiektów clientService i userAtuhenticationService np. do testówx
    public ClientController(UserAuthenticationService userAuthenticationService,
                            ClientService clientService) {
        this.userAuthenticationService = userAuthenticationService;
        this.clientService = clientService;
    }

    @RequestMapping(value = "/clients")
    public String clientsPage(HttpServletRequest request, ModelMap modelMap) throws JSONException, IOException, URISyntaxException {
        try {
            List<Client> firmClients = clientService.getFirmClients();
            Collections.sort(firmClients, Comparator.comparingInt(Client::getId));
            modelMap.addAttribute("authservice", userAuthenticationService);
            modelMap.addAttribute("clients", firmClients);
        } catch (HttpServerErrorException exception) {
            JSONObject obj = new JSONObject(exception.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            if (errorMessage.contains("company"))
                return "redirect:/chooseFirm?error="+errorMessage;
            
            return "redirect:/clients?error=" + errorMessage;
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
        try {
            clientService.addClient(name, additionalData);
        } catch (HttpServerErrorException exception) {
            JSONObject obj = new JSONObject(exception.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/clients?error=" + errorMessage;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/clients";
    }

    @RequestMapping(value = "/clients/editclient", method = RequestMethod.POST)
    public String editclient(@RequestParam("name") String name,
                             @RequestParam("additionalData") String additionalData,
                             @RequestParam("id") String id,
                            HttpServletRequest request,
                            ModelMap modelMap) throws URISyntaxException, JSONException {
        try {
            clientService.updateClient(Integer.parseInt(id), name, additionalData);
        } catch (HttpServerErrorException exception) {
            JSONObject obj = new JSONObject(exception.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/clients?error=" + errorMessage;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/clients";
    }

    @RequestMapping(value = "/clients/deleteclient/{id}", method = RequestMethod.GET)
    public String deleteClient(@PathVariable("id") String id) throws URISyntaxException, JSONException {
        try {
            clientService.deleteClient(Integer.parseInt(id));
        } catch (HttpServerErrorException exception) {
            JSONObject obj = new JSONObject(exception.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/clients?error=" + errorMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/clients";
    }
}
