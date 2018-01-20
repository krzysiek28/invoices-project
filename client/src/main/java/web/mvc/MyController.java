package web.mvc;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import web.mvc.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    @Autowired
    BankAccountService bankAccountService;


    @Autowired
    private FirmService firmService;



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
    public String homeLoged(ModelMap model) throws URISyntaxException, JSONException, IOException {
        model.addAttribute("authservice", userAuthenticationService);
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
            return "redirect:/loginPage?error=badcredentials&message=" + errorMessage;

        }

        return "redirect:/chooseFirm";
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
                           @RequestParam("personal") String personalData,
                           SessionStatus status) throws URISyntaxException, JSONException {
        try {
            userService.register(email, username, password, personalData);
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

        return "redirect:/chooseFirm";
    }

    @ExceptionHandler({ HttpServerErrorException.class})
    public String handleException(HttpServerErrorException ex) {
        JSONObject obj = null;
        try {
            obj = new JSONObject(ex.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/chooseFirm?error="+errorMessage;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return"redirect:/chooseFirm?error=Nieoczekiwany błąd!";
    }









//    @RequestMapping(value = "/clients/updateclient/{id}", method = RequestMethod.PUT)
//    public String



}