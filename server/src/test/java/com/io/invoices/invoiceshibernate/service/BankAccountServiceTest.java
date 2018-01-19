package com.io.invoices.invoiceshibernate.service;

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

public class BankAccountServiceTest {

    private BankAccountService testedObject;

    @Mock
    private FirmRepository mockedFirmRepository;
    @Mock
    private ApplicationUserRepository mockedUserRepository;
    private ApplicationUser owner1, owner2;

    @BeforeTest
    public void setTestedObject(){
        initMocks(this);
        //when(mockedUserRepository.find)
        //testedObject = new FirmService(mockedFirmRepository, null, null, null, mockedUserRepository, null);
        owner1 = new ApplicationUser(50,"Barbara","barbara123","barbara@gmail.com","USER_ROLE",true);
        owner2 = new ApplicationUser(51,"Bonifacy","bonifacy123","bonifacy@gmail.com","USER_ROLE",true);
        mockedUserRepository.save(owner1);
        when(mockedUserRepository.exists(50)).thenReturn(true);
        when(mockedUserRepository.exists(51)).thenReturn(true);

    }

    private List<Firm> getFirms(){
        List<Firm> result = new ArrayList<>();
        result.add(new Firm(1,"kompany1","323-424-13-32","+44876890234","Kraków","kompany1@yahoo.com",owner1));
        result.add(new Firm(2,"kompany2","523-424-13-36","+48876123434","Kraków","kompany2@gmail.com",owner1));
        result.add(new Firm(3,"kompany3","723-424-13-15","+44136630234","Katowice","kompany3@yahoo.com",owner2));
        return result;
    }

    @Test
    public void shouldReturnSpecificFirmsWhenOwnerIdPassed() throws Exception{
        List<Firm> firmList = getFirms();
        List<Firm> result = new ArrayList<>();

        firmList.forEach(e -> {
            if(e.getOwner().getId() == 50) result.add(e);
        });

        when(mockedFirmRepository.findByOwnerId(50)).thenReturn(result);
      //  List<Firm> foundFirms = testedObject.getFirms(50);
//        assertThat(foundFirms).isNotNull();
//        assertThat(foundFirms).isNotEmpty();
//        assertThat(foundFirms.size()).isEqualTo(result.size());
    }
}
