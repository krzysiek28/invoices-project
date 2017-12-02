package web.mvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/")
    public String homePage(){
        return "homePage";
    }

    @RequestMapping(value = "/log")
    public String logPage() {
        return "log";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerPage() {
        return "registrationPage";
    }

}