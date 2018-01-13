package com.io.invoices.invoiceshibernate.entity;

import com.io.invoices.invoiceshibernate.user.ApplicationUser;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationUserTest {
    private ApplicationUser testedObject;

    private final static Integer id = 13;
    private final static String email = "robert@test.com";
    private final static String username = "Robert Burn";
    private final static String password = "robert123";
    private final static String role = "USER_ROLE";
    private final static Boolean enabled = true;

    @BeforeTest
    public void setValues(){
        testedObject = new ApplicationUser(id,username,password,email,role,enabled);
    }

    @Test
    public void checkCreatedObject(){
        assertThat(testedObject).isNotNull();
        assertThat(testedObject.getId()).isEqualTo(13);
        assertThat(testedObject.getEmail()).isEqualTo("robert@test.com");
        assertThat(testedObject.getUsername()).isEqualTo("Robert Burn");
        assertThat(testedObject.getPassword()).isEqualTo("robert123");
        assertThat(testedObject.getRole()).isEqualTo("USER_ROLE");
        assertThat(testedObject.getEnabled()).isEqualTo(true);
    }
}
