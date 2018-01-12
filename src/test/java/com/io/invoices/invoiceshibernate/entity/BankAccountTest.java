package com.io.invoices.invoiceshibernate.entity;

import com.io.invoices.invoiceshibernate.bankAccount.BankAccount;
import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.user.ApplicationUser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BankAccountTest{

    private BankAccount testedObject;

    private final static String bankAccount = "23 3245 5659 0000 0032 2315";
    private final static String additionalData = "konto bankowe MojBank";
    ApplicationUser user = new ApplicationUser(50,"Barbara","barbara123","barbara@gmail.com","USER_ROLE",true);
    Firm firmId = new Firm(3,"kompany3","723-424-13-15","+44136630234","Katowice","kompany3@yahoo.com",user);

    @BeforeTest
    public void setValues(){
        testedObject = new BankAccount(bankAccount,additionalData,firmId);
    }

    @Test
    public void checkCreatedObject(){
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getFirm()).isEqualTo(firmId);
        assertThat(testedObject.getBankAccount()).isEqualTo(bankAccount);
        assertThat(testedObject.getAdditionalData()).isEqualTo(additionalData);
    }
}
