package com.io.invoices.invoiceshibernate.bankAccount;

import com.io.invoices.invoiceshibernate.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    UserRepository userRepository;

    public void addBankAccount(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    public List<BankAccount> getBankAccounts(String userName) {
        return bankAccountRepository.findBankAccountByUserName(userName);
    }

    public void deleteAccound(String accountId) {
        bankAccountRepository.delete(accountId);
    }
}
