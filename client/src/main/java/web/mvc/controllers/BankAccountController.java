package web.mvc.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import web.mvc.domain.BankAccount;
import web.mvc.service.BankAccountService;
import web.mvc.service.UserAuthenticationService;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class BankAccountController {

    UserAuthenticationService userAuthenticationService;
    BankAccountService bankAccountService;

    BankAccountController(UserAuthenticationService userAuthenticationService,
                          BankAccountService bankAccountService){
        this.userAuthenticationService = userAuthenticationService;
        this.bankAccountService = bankAccountService;
    }

    @RequestMapping(value = "/bankAccounts")
    public String bankAccountsPage(ModelMap modelMap) throws JSONException {

        try {
            List<BankAccount> bankAccounts = bankAccountService.getBankAccounts();
            modelMap.addAttribute("authservice", userAuthenticationService);
            modelMap.addAttribute("bankAccounts", bankAccounts);
        } catch (HttpServerErrorException exception) {
            JSONObject obj = new JSONObject(exception.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/bankAccounts?error=" + errorMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bankAccounts";
    }

    @RequestMapping(value = "/bankAccounts/addBankAccount")
    public String addBankAccount(@RequestParam("bankAccount") String bankAccount,
                                 @RequestParam("additionalData") String additionalData) throws URISyntaxException, JSONException {
        try {
            bankAccountService.addBankAccount(bankAccount, additionalData);
        } catch (HttpServerErrorException exception) {
            JSONObject obj = new JSONObject(exception.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/bankAccounts?error=" + errorMessage;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/bankAccounts";
    }

    @RequestMapping(value = "/bankAccounts/updateBankAccount/{id}")
    public String updateBankAccount(@RequestParam("additionalData") String additionalData,
                                    @PathVariable("id") String id) throws URISyntaxException, JSONException{
        try {
            bankAccountService.updateBankAccount(additionalData, id);
        } catch (HttpServerErrorException exception) {
            JSONObject obj = new JSONObject(exception.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/bankAccounts?error=" + errorMessage;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/bankAccounts";
    }

    @RequestMapping(value = "/bankAccounts/deleteBankAccount/{id}")
    public String deleteBankAccount(@PathVariable("id") String id) throws URISyntaxException, JSONException {
        try {
            bankAccountService.deleteBankAccount(id);
        } catch (HttpServerErrorException exception) {
            JSONObject obj = new JSONObject(exception.getResponseBodyAsString());
            String errorMessage = obj.getString("message");
            return "redirect:/bankAccounts?error=" + errorMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/bankAccounts";
    }
}
