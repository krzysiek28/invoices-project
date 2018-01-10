package com.io.invoices.invoiceshibernate.bankAccount;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BankAccountRepository extends CrudRepository<BankAccount,String>{

    List<BankAccount> findBankAccountByFirmId(Integer firmId);
    @Transactional
    long deleteBankAccountsByFirmId(Integer firmId);
}
