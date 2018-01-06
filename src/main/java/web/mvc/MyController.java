package web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import web.mvc.domain.Usery;
import web.mvc.service.ClientService;
import web.mvc.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

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
    public String loggedPage() {
        return "homeLogged";
    }

    @RequestMapping(value = "/logged")
    public String logPage() {
        return "logged";
    }

    @RequestMapping(value = "/registrationPage")
    public String register() {
        return "registrationPage";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String addusers(@ModelAttribute("email") String email,
                           @ModelAttribute("password") String password,
                           ModelMap modelMap){

        String sqlUser = String.format("INSERT INTO usery(email,password,enabled) VALUES ('%s','%s',true)", email, password);
        jdbcTemplate.execute(sqlUser);
        String sqlRole = String.format("UPDATE usery set role='%s' where email='%s'", "ROLE_USER",email);
        jdbcTemplate.execute(sqlRole);
        List<Usery> userList = jdbcTemplate.query("select * from usery", new BeanPropertyRowMapper<>(Usery.class));
        modelMap.addAttribute("usery",userList);

        return "homePage";
    }

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

    @RequestMapping(value = "/clients/deleteclient/{id}", method = RequestMethod.GET)
    public String deleteClient(@PathVariable("id") String id){
        try {
            clientService.deleteClientById(id);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "redirect:/clients";
    }

    @RequestMapping(value = "/products/deleteproduct/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") String id){
        try {
            productService.deleteProductById(id);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }

//    @RequestMapping(value = "/clients/updateclient/{id}", method = RequestMethod.PUT)
//    public String

    @RequestMapping(value = "/createfacture")
    public String createf() {
        return "createfacture";
    }

}