package com.io.invoices.invoiceshibernate.firmUsers;

import com.io.invoices.invoiceshibernate.firm.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirmUsersService {
    @Autowired
    FirmUsersRepository firmUsersRepository;
    @Autowired
    FirmService firmService;

    public void addAllUsersToFirm(List<FirmUsers> firmUsers){
        firmUsersRepository.save(firmUsers);
    }
}
