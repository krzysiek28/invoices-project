package com.io.invoices.invoiceshibernate.entity;

import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.user.ApplicationUser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FirmTest {

    private Firm testedObject;

    private final static Integer id = 1;
    private final static String name = "company for test";
    private final static String nip = "423-222-12-12";
    private final static String phone = "+48555000555";
    private final static String place = "Kraków";
    private final static String email = "company@yahoo.com";
    private final static ApplicationUser owner = new ApplicationUser(50,"Barbara","barbara123","barbara@gmail.com","USER_ROLE",true);

    @BeforeTest
    public void setValues(){
        testedObject = new Firm(id,name,nip,phone,place,email,owner);
    }

    @Test
    public void checkCreatedObject(){
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getId()).isEqualTo(id);
        assertThat(testedObject.getName()).isEqualTo(name);
        assertThat(testedObject.getNip()).isEqualTo(nip);
        assertThat(testedObject.getPhone()).isEqualTo(phone);
        assertThat(testedObject.getPlace()).isEqualTo(place);
        assertThat(testedObject.getEmail()).isEqualTo(email);
        assertThat(testedObject.getOwner()).isEqualTo(owner);
    }
}
