package com.io.invoices.invoiceshibernate.firmUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirmUsersService {
    @Autowired
    FirmUsersRepository firmUsersRepository;

    public void addAllUsersToFirm(List<FirmUsers> firmUsers){
        firmUsersRepository.save(firmUsers);
    }

}
