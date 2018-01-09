package web.mvc.controllers;


import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import web.mvc.service.FirmService;
import web.mvc.service.UserAuthenticationService;
import web.mvc.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
public class FirmController {

    private UserAuthenticationService userAuthenticationService;
    private FirmService firmService;
    private UserService userService;

    public FirmController(UserAuthenticationService userAuthenticationService,
                            FirmService firmService, UserService userService) {
        this.userAuthenticationService = userAuthenticationService;
        this.firmService = firmService;
        this.userService = userService;
    }

    @RequestMapping(value = "/chooseFirm")
    public String chooseFirm(ModelMap modelMap) throws URISyntaxException, JSONException, IOException {
        modelMap.addAttribute("authservice", userAuthenticationService);
        if (userAuthenticationService.isLoggedIn()) {
            userService.setUserId();
            modelMap.addAttribute("firms", firmService.getFirms());
        }
        return "chooseFirm";
    }

    @RequestMapping(value = "/chooseFirm/{id}")
    public String setFirmId(@PathVariable("id") String id){
        userAuthenticationService.setFirmId(Integer.parseInt(id));

        return "homeLogged";
    }

    @RequestMapping(value = "/firms")
    public String menageFirm(HttpServletRequest request,
                             ModelMap modelMap) {
        try {
            modelMap.addAttribute("firms", firmService.getFirms());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "firms";
    }

    @RequestMapping(value = "/firms/addfirm", method = RequestMethod.POST)
    public String addFirm(@RequestParam("email") String email,
                          @RequestParam("name") String name,
                          @RequestParam("nip") String nip,
                          @RequestParam("phone") String phone,
                          @RequestParam("place") String place,
                          SessionStatus status) throws URISyntaxException, JSONException {
        firmService.addFirm(email, name, nip, phone, place);
        status.setComplete();

        return "redirect:/firms";
    }
}
