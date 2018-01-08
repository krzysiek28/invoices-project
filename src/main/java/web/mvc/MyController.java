package web.mvc;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import web.mvc.service.ClientService;
import web.mvc.service.UserAuthenticationService;
import web.mvc.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

@Controller
public class MyController {

    @Autowired
    UserAuthenticationService userAuthenticationService;
    @Autowired
    ClientService clientService;
    @Autowired
    private RestTemplate restTemplateHCCHRF;
    @Autowired
    private UserService userService;

    @RequestMapping("/logout")
    public String logout() {
        userAuthenticationService.logout();
        return "redirect:/";
    }

    @RequestMapping(value = "/")
    public String homePage(ModelMap model) {
        model.addAttribute("authservice", userAuthenticationService);
        return "homePage";
    }

    @RequestMapping(value = "/loginPage")
    public String loginPage(ModelMap model, SessionStatus status) {
        model.addAttribute("authservice", userAuthenticationService);
        status.setComplete();
        return "loginPage";
    }

    @RequestMapping(value = "/homeLogged")
    public String homeLoged(ModelMap model) throws URISyntaxException, JSONException {
        model.addAttribute("authservice", userAuthenticationService);
        if (userAuthenticationService.isLoggedIn())
            userService.setUserId();
        return "homeLogged";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loggedPage(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             SessionStatus status,
                             Model model,
                             HttpServletRequest request) throws URISyntaxException, JSONException {
        try {
            userService.login(username, password);
            status.setComplete();
        } catch (HttpStatusCodeException exception) {
            JSONObject obj = new JSONObject(exception.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/loginPage?error=yes&message=" + errorMessage;

        }

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
                           SessionStatus status) throws URISyntaxException, JSONException {
        try {
            userService.register(email, username, password);
            status.setComplete();
        } catch (HttpStatusCodeException e) {
            JSONObject obj = new JSONObject(e.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            String errorType;
            switch (errorMessage) {
                case "Username already exists!":
                    errorType = "username";
                    break;
                case "User with provided email already exists!":
                    errorType = "email";
                    break;
                default:
                    errorType = errorMessage;
                    break;
            }
            return "redirect:/registrationPage?error=yes&message=" + errorType;
        }

        return "redirect:/homeLogged";
    }


    @RequestMapping(value = "/products")
    public String productsPage(HttpServletRequest request,
                               ModelMap modelMap) {
        modelMap.addAttribute("authservice", userAuthenticationService);

//        try {
//            modelMap.addAttribute("products", productService.getProducts());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return "products";
    }

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