package com.io.invoices.invoiceshibernate;

import com.io.invoices.invoiceshibernate.bankAccount.BankAccountService;
import com.io.invoices.invoiceshibernate.firm.FirmService;
import com.io.invoices.invoiceshibernate.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvoicesHibernateApplication implements CommandLineRunner{

	@Autowired
	FirmService firmService;
	@Autowired
	UserService userService;
	@Autowired
	BankAccountService bankAccountService;

	public static void main(String[] args) {
		SpringApplication.run(InvoicesHibernateApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception { }
}
