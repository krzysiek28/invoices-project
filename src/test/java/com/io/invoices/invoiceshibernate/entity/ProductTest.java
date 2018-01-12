package com.io.invoices.invoiceshibernate.entity;

import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.product.Product;
import com.io.invoices.invoiceshibernate.user.ApplicationUser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ProductTest {
    private Product testedObject;

    private final static Integer id = 1;
    private final static String name = "dynamit";
    private final static String unit = "kg";
    private final static Float netUnitPrice = 100.00f;
    private final static Float vatRate = 23.00f;
    private final static ApplicationUser user = new ApplicationUser(50,"Barbara","barbara123","barbara@gmail.com","USER_ROLE",true);
    private final static Firm firm = new Firm(1,"kompany1","323-424-13-32","+44876890234","Kraków","kompany1@yahoo.com",user);

    @BeforeTest
    public void setValues(){
        testedObject = new Product(id,firm,name,netUnitPrice,vatRate,unit);
    }

    @Test
    public void checkCreatedObject(){
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getId()).isEqualTo(id);
        assertThat(testedObject.getName()).isEqualTo(name);
        assertThat(testedObject.getVatRate()).isEqualTo(vatRate);
        assertThat(testedObject.getNetUnitPrice()).isEqualTo(netUnitPrice);
        assertThat(testedObject.getUnit()).isEqualTo(unit);
        assertThat(testedObject.getOwner()).isEqualTo(firm);

    }
}
