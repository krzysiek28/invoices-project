package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.bankAccount.BankAccount;
import com.io.invoices.invoiceshibernate.bankAccount.BankAccountRepository;
import com.io.invoices.invoiceshibernate.bankAccount.BankAccountService;
import com.io.invoices.invoiceshibernate.user.User;
import com.io.invoices.invoiceshibernate.user.UserRepository;
import com.io.invoices.invoiceshibernate.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping (value = "/bank")
public class BankAccountController {
    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    UserService userService;

    @RequestMapping("/{username}/accounts")
    public List<BankAccount> getBankAccounts(@PathVariable String username) {
        return bankAccountService.getBankAccounts(username);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{username}/accounts")
    public void addBankAccount(@RequestBody BankAccount bankAccount, @PathVariable String username) {
        //todo user username to authorize
        User user = userService.getUser(username);
        bankAccount.setUser(user);
        bankAccountService.addBankAccount(bankAccount);
    }

}
