package com.io.invoices.invoiceshibernate.bankAccount;

import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.firm.FirmRepository;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final FirmRepository firmRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository, FirmRepository firmRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.firmRepository = firmRepository;
    }

    public void addBankAccount(Integer firmId, BankAccount bankAccount) {
        if (!firmRepository.exists(firmId))
            throw new IllegalArgumentException("Firma o podanym id nie istnieje!");

        if (bankAccountRepository.exists(bankAccount.getBankAccount()))
            throw new IllegalArgumentException("Ten numer konta bankowego już istnieje w bazie");

        Firm owner = firmRepository.findOne(firmId);
        bankAccount.setFirm(owner);
        bankAccount.stripTags();
        if (!bankAccount.isCorrect())
            throw new IllegalArgumentException("Podano niepoprawne dane!");

        bankAccountRepository.save(bankAccount);
    }

    public List<BankAccount> getBankAccounts(Integer firmId) {
        if (!firmRepository.exists(firmId))
            throw new IllegalArgumentException("Podana firma nie istnieje!");

        return bankAccountRepository.findBankAccountByFirmId(firmId);
    }

    public void deleteAccount(String accountId) {
        if (!bankAccountRepository.exists(accountId))
            throw new IllegalArgumentException("Podane konto nie istnieje!");

        bankAccountRepository.delete(accountId);
    }

    public void updateAccount(String accountId, BankAccount account) {
        if (!bankAccountRepository.exists(accountId))
            throw new IllegalArgumentException("Podane konto nie istnieje!");

        BankAccount dbBankAccount = bankAccountRepository.findOne(accountId);

        account.stripTags();
        if(!account.isCorrect())
            throw new IllegalArgumentException("Podano niepoprawne dane!");

        dbBankAccount.setBankAccount(account.getBankAccount());
        dbBankAccount.setAdditionalData(account.getAdditionalData());
        bankAccountRepository.save(dbBankAccount);
    }
}
