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

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/")
    public String homePage(){
        return "homePage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/log")
    public String logPage() {
        return "log";
    }

    @RequestMapping(value = "/registrationPage")
    public String register() {
        return "registrationPage";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String addusers(@ModelAttribute("email") String email,
                           @ModelAttribute("password") String password,
                           ModelMap modelMap){ // bo z posta

        String sqlUser = String.format("INSERT INTO users(email,password,enabled) VALUES ('%s','%s',true)", email, password);
        jdbcTemplate.execute(sqlUser);
        String sqlRole = String.format("INSERT INTO user_roles(email,role) VALUES ('%s','%s')",email, "ROLE_USER");
        jdbcTemplate.execute(sqlRole);
        List<User> userList = jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
        modelMap.addAttribute("users",userList);

        return "homePage";
    }

    @RequestMapping(value = "/createfacture")
    public String createf() {
        return "createfacture";
    }

    @RequestMapping(value = "/userdata")
    public String goToUserData() {
        return "userdata";
    }


}