package com.io.invoices.invoiceshibernate.bankAccount;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankAccountRepository extends CrudRepository<BankAccount,String>{
    List<BankAccount> findBankAccountsByUserName(String name);
}
