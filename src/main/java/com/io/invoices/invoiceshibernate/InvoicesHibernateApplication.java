package com.io.invoices.invoiceshibernate;

import com.io.invoices.invoiceshibernate.bankAccount.BankAccount;
import com.io.invoices.invoiceshibernate.bankAccount.BankAccountService;
import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.firm.FirmService;
import com.io.invoices.invoiceshibernate.firmUsers.FirmUsers;
import com.io.invoices.invoiceshibernate.firmUsers.FirmUsersService;
import com.io.invoices.invoiceshibernate.user.User;
import com.io.invoices.invoiceshibernate.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class InvoicesHibernateApplication implements CommandLineRunner{

	@Autowired
	FirmService firmService;
	@Autowired
	UserService userService;
	@Autowired
	FirmUsersService firmUsersService;
	@Autowired
	BankAccountService bankAccountService;

	public static void main(String[] args) {
		SpringApplication.run(InvoicesHibernateApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
	/*	List<Firm> firms = new ArrayList<>();
		firms.add(new Firm("Valo","232-323-21","Kraków","432 434 324","valo@gmail.com"));
		firms.add(new Firm("Mak","232-888-21","Warszawa","132 434 324","mak@gmail.com"));
		firmService.addFirms(firms);

		List<User> users = new ArrayList<>();
		users.add(new User("Anna Kowalska","anna@gmail.com","anna123"));
		users.add(new User("Bartłomiej Kluska","klucha@gmail.com","kluchy123"));
		userService.addUsers(users);

		List<FirmUsers> firmUsers = new ArrayList<>();
		firmUsers.add(new FirmUsers(users.get(0),firms.get(0)));
		firmUsers.add(new FirmUsers(users.get(1),firms.get(0)));
		firmUsersService.addAllUsersToFirm(firmUsers);

		bankAccountService.addBankAccount(new BankAccount("2131 2343 2141",users.get(0)));
		bankAccountService.addBankAccount(new BankAccount("2222 3333 1232",users.get(0)));
		*/
	}
}
