package com.io.invoices.invoiceshibernate.userFirm;

import com.io.invoices.invoiceshibernate.firm.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFirmService {
    @Autowired
    UserFirmRepository userFirmRepository;
    @Autowired
    FirmService firmService;

    public void addAllUsersToFirm(List<UserFirm> userFirmUsers){
        userFirmRepository.save(userFirmUsers);
    }
}
