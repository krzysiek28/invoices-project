package com.io.invoices.invoiceshibernate.bankAccount;

import com.io.invoices.invoiceshibernate.user.User;
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

    public void addBankAccount(Integer userId, BankAccount bankAccount) {
        if (!userRepository.exists(userId)) {
            throw new IllegalArgumentException("Account owner does not exist!");
        }

        User accountOwner = userRepository.findOne(userId);
        bankAccount.setUser(accountOwner);
        bankAccountRepository.save(bankAccount);
    }

    public List<BankAccount> getBankAccounts(Integer userId) {
        return bankAccountRepository.findBankAccountByUserId(userId);
    }

    public void deleteAccound(String accountId) {
        bankAccountRepository.delete(accountId);
    }
}
