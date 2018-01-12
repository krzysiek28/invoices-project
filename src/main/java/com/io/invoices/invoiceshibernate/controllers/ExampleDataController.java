package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.bankAccount.BankAccount;
import com.io.invoices.invoiceshibernate.bankAccount.BankAccountRepository;
import com.io.invoices.invoiceshibernate.client.Client;
import com.io.invoices.invoiceshibernate.client.ClientRepository;
import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.firm.FirmRepository;
import com.io.invoices.invoiceshibernate.product.Product;
import com.io.invoices.invoiceshibernate.product.ProductRepository;
import com.io.invoices.invoiceshibernate.security.SecurityUtils;
import com.io.invoices.invoiceshibernate.user.ApplicationUser;
import com.io.invoices.invoiceshibernate.user.ApplicationUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleDataController {

    private final FirmRepository firmRepository;
    private final ClientRepository clientRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ProductRepository productRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ApplicationUserRepository applicationUserRepository;

    public ExampleDataController(
            FirmRepository firmRepository,
            ClientRepository clientRepository,
            BankAccountRepository bankAccountRepository,
            ProductRepository productRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ApplicationUserRepository applicationUserRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.firmRepository = firmRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.applicationUserRepository = applicationUserRepository;
    }

    @RequestMapping("/exampledata")
    public String exampleData() {

        ApplicationUser user = new ApplicationUser("arek",bCryptPasswordEncoder.encode("arek"),"arek@arek.pl","lol","Arek Poziomka", true);
        applicationUserRepository.save(user);

        Firm firma_a = new Firm("Firma A", "123-123-12-12", "ul. Słoneczna 166 \n 12-123 Kraków", "123123123", "firmaa@wp.pl", user);
        firmRepository.save(firma_a);
        Firm firma_b = new Firm("Firma B", "666-66-66-666", "ul. Zielona 166 \n 12-123 Gdańsk", "777777777", "firmab@wp.pl", user);
        firmRepository.save(firma_b);

        Client ca1 = new Client("Jeż Jerzy", "Jeża Jerzego Adres \n Telefon jego", firma_a);
        Client ca2 = new Client("Słoń Jacek", "Słoński Adres \n Telefon słonia", firma_a);
        clientRepository.save(ca1);
        clientRepository.save(ca2);

        BankAccount ba1 = new BankAccount("123412341234123412341234","Bank USB S.A.", firma_a);
        BankAccount ba2 = new BankAccount("789078907890789078907890","Bank XDLOL S.A.", firma_a);
        bankAccountRepository.save(ba1);
        bankAccountRepository.save(ba2);

        Product pa1 = new Product(firma_a, "Ogórki", new Float(12.12), new Float(12), "kg", "PLN");
        Product pa2 = new Product(firma_a, "Cytryny", new Float(34.4), new Float(15), "kg", "PLN");
        productRepository.save(pa2);
        productRepository.save(pa1);

        return firma_a.getId()+ "\n" + SecurityUtils.generateToken(user.getUsername());
    }
}
