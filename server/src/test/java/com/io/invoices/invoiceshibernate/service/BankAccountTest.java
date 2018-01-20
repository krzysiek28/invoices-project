package com.io.invoices.invoiceshibernate.service;

import com.io.invoices.invoiceshibernate.bankAccount.BankAccount;
import com.io.invoices.invoiceshibernate.bankAccount.BankAccountController;
import com.io.invoices.invoiceshibernate.bankAccount.BankAccountRepository;
import com.io.invoices.invoiceshibernate.bankAccount.BankAccountService;
import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.firm.FirmRepository;
import com.io.invoices.invoiceshibernate.firm.FirmService;
import com.io.invoices.invoiceshibernate.user.ApplicationUser;
import com.io.invoices.invoiceshibernate.user.ApplicationUserRepository;
import org.mockito.Mock;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BankAccountTest {

    private BankAccountService testedService;
    private BankAccountController testedController;

    @Mock
    private FirmRepository mockedFirmRepository;
    @Mock
    private BankAccountRepository mockedBankRepository;

    @BeforeTest
    public void setTestedObject(){
        initMocks(this);
        testedService = new BankAccountService(mockedBankRepository, mockedFirmRepository);
        testedController = new BankAccountController(testedService,null);

        Firm firm = new Firm();
        firm.setId(1);
        BankAccount account = new BankAccount("1234","Dane", firm);
        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(account);
        when(mockedBankRepository.findBankAccountByFirmId(1)).thenReturn(accounts);
        when(mockedFirmRepository.exists(1)).thenReturn(true);

    }

    @Test
    public void shouldReturnBankAccounts() throws Exception{
        List<BankAccount> bankAccounts = testedController.getBankAccounts("1",null);


        assertThat(bankAccounts).element(0).hasFieldOrPropertyWithValue("bankAccount","1234");
        assertThat(bankAccounts).element(0).hasFieldOrPropertyWithValue("additionalData", "Dane");
        assertThat(bankAccounts).hasSize(1);
    }
}
