package com.io.invoices.invoiceshibernate.entity;

import com.io.invoices.invoiceshibernate.client.Client;
import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.user.ApplicationUser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ClientTest {
    private Client testedObject;

    private final static Integer id = 1;
    private final static String name = "Anna Kowalik";
    private final static String additionalData = "Client for test";
    private final static ApplicationUser owner = new ApplicationUser(50,"Barbara","barbara123","barbara@gmail.com","USER_ROLE",true);
    private final static Firm firm = new Firm(1,"kompany1","323-424-13-32","+44876890234","Kraków","kompany1@yahoo.com",owner);

    @BeforeTest
    public void setValues(){
        testedObject = new Client(id,name,additionalData,firm);
    }

    @Test
    public void checkCreatedObject(){
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getId()).isEqualTo(id);
        assertThat(testedObject.getName()).isEqualTo(name);
        assertThat(testedObject.getAdditionalData()).isEqualTo(additionalData);
        assertThat(testedObject.getOwner()).isEqualTo(firm);
    }
}
