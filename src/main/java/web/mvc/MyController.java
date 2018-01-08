package web.mvc;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import web.mvc.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

@Controller
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String homePage(){
        return "homePage";
    }

    @RequestMapping(value = "/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @RequestMapping(value = "/homeLogged")
    public String homeLoged() {return "homeLogged";}

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loggedPage(@ModelAttribute("username") String username,
                             @ModelAttribute("password") String password) throws URISyntaxException {
        userService.login(username, password);
        return "homeLogged";
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
    public String register(@ModelAttribute("email") String email,
                           @ModelAttribute("username") String username,
                           @ModelAttribute("password") String password) throws URISyntaxException {
        userService.register( email, username, password);
        return "homeLogged";
    }

/*
    @RequestMapping(value = "/products")
    public String productsPage(HttpServletRequest request,
                               ModelMap modelMap) {
        try {
            modelMap.addAttribute("products", productService.getProductsByOwnerID((request.getUserPrincipal().getName())));
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