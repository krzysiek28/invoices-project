package com.io.invoices.invoiceshibernate.service;

import com.io.invoices.invoiceshibernate.user.ApplicationUserRepository;
import com.io.invoices.invoiceshibernate.user.UserService;
import org.mockito.Mock;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {
    UserService testedObject;

    @Mock
    private ApplicationUserRepository mockedApplicationUserRepository;

    @BeforeTest
    public void setTestedObject(){
        initMocks(this);
        testedObject = new UserService(mockedApplicationUserRepository);
    }

}
