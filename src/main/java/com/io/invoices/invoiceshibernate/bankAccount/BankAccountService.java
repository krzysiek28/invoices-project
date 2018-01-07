package com.io.invoices.invoiceshibernate.bankAccount;

import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.firm.FirmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    FirmRepository firmRepository;

    public void addBankAccount(Integer firmId, BankAccount bankAccount) {
        if (!firmRepository.exists(firmId)) {
            throw new IllegalArgumentException("Company does not exist!");
        }

        Firm owner = firmRepository.findOne(firmId);
        bankAccount.setFirm(owner);
        bankAccountRepository.save(bankAccount);
    }

    public List<BankAccount> getBankAccounts(Integer firmId) {
        return bankAccountRepository.findBankAccountByFirmId(firmId);
    }

    public void deleteAccount(String accountId) {
        if (!bankAccountRepository.exists(accountId))
            throw new IllegalArgumentException("Bank account does not exist!");
        bankAccountRepository.delete(accountId);
    }

    public void updateAccount(String accountId, BankAccount account) {
        if (!bankAccountRepository.exists(accountId))
            throw new IllegalArgumentException("Bank account does not exist!");

        BankAccount dbBankAccount = bankAccountRepository.findOne(accountId);
        dbBankAccount.setBankAccount(account.getBankAccount());
        dbBankAccount.setAdditionalData(account.getAdditionalData());
        bankAccountRepository.save(dbBankAccount);
    }
}
