package com.io.invoices.invoiceshibernate.bankAccount;

import com.io.invoices.invoiceshibernate.bankAccount.BankAccount;
import com.io.invoices.invoiceshibernate.bankAccount.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/bankaccounts")
public class BankAccountController {
    @Autowired
    BankAccountService bankAccountService;

    @RequestMapping("/{firmId}")
    public List<BankAccount> getBankAccounts(@PathVariable String firmId) {
        return bankAccountService.getBankAccounts(Integer.parseInt(firmId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{firmId}")
    public void addBankAccount(@RequestBody BankAccount bankAccount, @PathVariable String firmId) {
        bankAccountService.addBankAccount(Integer.parseInt(firmId), bankAccount);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{firmId}/{accountId}")
    public void deleteAccount(@PathVariable String accountId) {
        bankAccountService.deleteAccount(accountId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{firmId}/{accountId}")
    public void updateAccount(@PathVariable String accountId, @RequestBody BankAccount account) {
        bankAccountService.updateAccount(accountId, account);
    }
}
