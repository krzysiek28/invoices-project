package web.mvc;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import web.mvc.service.UserAuthenticationService;
import web.mvc.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

@Controller
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @RequestMapping("/logout")
    public String logout() {
        userAuthenticationService.logout();
        return "redirect:/";
    }

    @RequestMapping(value = "/")
    public String homePage(ModelMap model){
        model.addAttribute("authservice", userAuthenticationService);
        return "homePage";
    }

    @RequestMapping(value = "/loginPage")
    public String loginPage( SessionStatus status) {
        status.setComplete();
        return "loginPage";
    }

    @RequestMapping(value = "/homeLogged")
    public String homeLoged(ModelMap model) {
        model.addAttribute("authservice", userAuthenticationService);
        return "homeLogged";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loggedPage(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             SessionStatus status,
                             Model model) throws URISyntaxException {
        userService.login(username, password);
        status.setComplete();
//        model.asMap().clear();

        return "redirect:/homeLogged";
    }

    @RequestMapping(value = "/logged")
    public String logPage() {
        return "logged";
    }

    @RequestMapping(value = "/registrationPage")
    public String registrationPage() {
        return "registrationPage";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String register(@RequestParam("email") String email,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           SessionStatus status) throws URISyntaxException {
        userService.register( email, username, password);
        status.setComplete();

        return "redirect:/homeLogged";
    }

/*
    @RequestMapping(value = "/products")
    public String productsPage(HttpServletRequest request,
                               ModelMap modelMap) {
        try {
            modelMap.addAttribute("products", productService.getProducts());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "products";
    }
*/
/*
    @RequestMapping(value = "/products/addproduct", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("name") String name,
                            @ModelAttribute("netUnitPrice") Float netUnitPrice,
                            @ModelAttribute("unit") String unit,
                            @ModelAttribute("vatRate") Float vatRate,
                            HttpServletRequest request,
                            ModelMap modelMap) {
        try {
            modelMap.addAllAttributes(productService.addProduct(name, netUnitPrice, unit, vatRate, productService.getOwnerId(request.getUserPrincipal().getName())));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }
*/
/*
    @RequestMapping(value = "/clients")
    public String clientsPage(HttpServletRequest request,
                              ModelMap modelMap) {
        try {
            modelMap.addAttribute("clients", clientService.getClientsByOwnerID(request.getUserPrincipal().getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "clients";
    }
*/
/*
    @RequestMapping(value = "/clients/addclient", method = RequestMethod.POST)
    public String addClient(@ModelAttribute("name") String name,
                           @ModelAttribute("additionalData") String additionalData,
                           HttpServletRequest request,
                           ModelMap modelMap) {
        try {
            modelMap.addAllAttributes(clientService.addClient(name, additionalData, clientService.getOwnerId(request.getUserPrincipal().getName())));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "redirect:/clients";
    }
*/
/*
    @RequestMapping(value = "/clients/deleteclient/{id}", method = RequestMethod.GET)
    public String deleteClient(@PathVariable("id") String id){
        try {
            clientService.deleteClientById(id);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "redirect:/clients";
    }
*/
/*
    @RequestMapping(value = "/products/deleteproduct/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") String id){
        try {
            productService.deleteProductById(id);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }
*/
//    @RequestMapping(value = "/clients/updateclient/{id}", method = RequestMethod.PUT)
//    public String

    @RequestMapping(value = "/createfacture")
    public String createf() {
        return "createfacture";
    }

}