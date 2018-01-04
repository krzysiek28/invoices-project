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
@RequestMapping (value = "/bankaccounts")
public class BankAccountController {
    @Autowired
    BankAccountService bankAccountService;

    @RequestMapping("/{userId}")
    public List<BankAccount> getBankAccounts(@PathVariable String userId) {
        return bankAccountService.getBankAccounts(Integer.parseInt(userId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{userId}")
    public void addBankAccount(@RequestBody BankAccount bankAccount, @PathVariable String userId) {
        bankAccountService.addBankAccount(Integer.parseInt(userId), bankAccount);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}/{accountId}")
    public void deleteAccount(@PathVariable String accountId) {
        bankAccountService.deleteAccound(accountId);
    }

}
