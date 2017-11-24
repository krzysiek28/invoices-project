package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.bankAccount.BankAccount;
import com.io.invoices.invoiceshibernate.bankAccount.BankAccountRepository;
import com.io.invoices.invoiceshibernate.bankAccount.BankAccountService;
import com.io.invoices.invoiceshibernate.user.UserRepository;
import com.io.invoices.invoiceshibernate.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping (value = "/Account")
public class BankAccountController {
    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    UserService userService;

    @GetMapping (value = "/{name}")
    public List<BankAccount> getBankAccountByUserName(@PathVariable String name){
        return bankAccountService.getBankAccountByName(name);
    }

}
