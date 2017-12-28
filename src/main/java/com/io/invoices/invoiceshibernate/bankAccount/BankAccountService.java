package com.io.invoices.invoiceshibernate.bankAccount;

import com.io.invoices.invoiceshibernate.user.User;
import com.io.invoices.invoiceshibernate.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    UserRepository userRepository;

    public void addBankAccount(BankAccount bankAccount){
        bankAccountRepository.save(bankAccount);
    }

    public List<BankAccount> getBankAccountByName(String name){
        List<BankAccount> bankAccounts = new ArrayList<>();
        Iterable<BankAccount> iterable = bankAccountRepository.findBankAccountsByUserName(name);
        iterable.forEach(e -> bankAccounts.add(e));
        return bankAccounts;
    }

}
