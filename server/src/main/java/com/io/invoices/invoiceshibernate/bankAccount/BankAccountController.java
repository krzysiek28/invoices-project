package com.io.invoices.invoiceshibernate.bankAccount;

import com.io.invoices.invoiceshibernate.security.AuthorizationFilter;
import com.io.invoices.invoiceshibernate.security.ResourceType;
import com.io.invoices.invoiceshibernate.security.UnauthorizedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bankaccounts")
public class BankAccountController {
    
    private final BankAccountService bankAccountService;
    private final AuthorizationFilter authorizationFilter;

    public BankAccountController(BankAccountService bankAccountService, AuthorizationFilter authorizationFilter) {
        this.bankAccountService = bankAccountService;
        this.authorizationFilter = authorizationFilter;
    }

    @RequestMapping("/{firmId}")
    public List<BankAccount> getBankAccounts(@PathVariable String firmId,@RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,firmId, ResourceType.FIRM);
        return bankAccountService.getBankAccounts(Integer.parseInt(firmId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{firmId}")
    public void addBankAccount(@RequestBody BankAccount bankAccount, @PathVariable String firmId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,firmId, ResourceType.FIRM);
        bankAccountService.addBankAccount(Integer.parseInt(firmId), bankAccount);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{firmId}/{accountId}")
    public void deleteAccount(@PathVariable String accountId,@RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,accountId,ResourceType.BANKACCOUNT);
        bankAccountService.deleteAccount(accountId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{firmId}/{accountId}")
    public void updateAccount(@PathVariable String accountId, @RequestBody BankAccount account,@RequestHeader("Authorization") String token) throws UnauthorizedException {
        authorizationFilter.isAuthorizedTo(token,accountId,ResourceType.BANKACCOUNT);
        bankAccountService.updateAccount(accountId, account);
    }
}
