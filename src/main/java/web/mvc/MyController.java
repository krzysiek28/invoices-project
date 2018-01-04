package web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import web.mvc.domain.Usery;
import web.mvc.service.ClientService;

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
        return "logged";
    }

    @RequestMapping(value = "/logged")
    public String logPage() {
        return "logged";
    }

    @RequestMapping(value = "/products")
    public String productsPage() {
        return "products";
    }

    @RequestMapping(value = "/clients")
    public String clientsPage(ModelMap modelMap) {
        try {
            modelMap.addAttribute("clients", clientService.getClients());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "clients";
    }

    @RequestMapping(value = "/clients/addclient", method = RequestMethod.POST)
    public String addActor(@ModelAttribute("name") String name,
                           @ModelAttribute("additionalData") String additionalData,
                           /*@ModelAttribute("owner") web.mvc.domain.Usery owner,*/
                           ModelMap modelMap) {
        try {
            modelMap.addAllAttributes(clientService.addClient(name, additionalData/*, owner*/));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "redirect:/clients";
    }

    @RequestMapping(value = "/registrationPage")
    public String register() {
        return "registrationPage";
    }

//    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
//    public String addusers(@ModelAttribute("email") String email,
//                           @ModelAttribute("password") String password,
//                           ModelMap modelMap){ // bo z posta
//
//        String sqlUser = String.format("INSERT INTO users(email,password,enabled) VALUES ('%s','%s',true)", email, password);
//        jdbcTemplate.execute(sqlUser);
//        String sqlRole = String.format("INSERT INTO user_roles(email,role) VALUES ('%s','%s')",email, "ROLE_USER");
//        jdbcTemplate.execute(sqlRole);
//        List<Usery> userList = jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(Usery.class));
//        modelMap.addAttribute("users",userList);
//
//        return "homePage";
//    }

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

    @RequestMapping(value = "/createfacture")
    public String createf() {
        return "createfacture";
    }

}