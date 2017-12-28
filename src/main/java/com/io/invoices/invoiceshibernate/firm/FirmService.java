package com.io.invoices.invoiceshibernate.firm;

import com.io.invoices.invoiceshibernate.firmUsers.FirmUsers;
import com.io.invoices.invoiceshibernate.firmUsers.FirmUsersRepository;
import com.io.invoices.invoiceshibernate.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirmService {

    @Autowired
    FirmRepository firmRepository;

    @Autowired
    FirmUsersRepository firmUsersRepository;

    public void addFirm(Firm firm){
        firmRepository.save(firm);
    }

    //method implements only for development
    public void addFirms(List<Firm> firms){
        firmRepository.save(firms);
    }

    public List<Firm> getFirms(){
        List<Firm> firms = new ArrayList<>();
        Iterable<Firm> iterable = firmRepository.findAll();
        iterable.forEach(e -> firms.add(e));
        return firms;
    }

    public void deleteFirmById(Integer id){
        firmRepository.delete(id);
    }

    public List<User> findFirmUsersByFirm_Name(String name){
        List<User> firmUsers = new ArrayList<>();
        firmUsersRepository.findFirmUsersByFirm_Name(name).forEach(e -> firmUsers.add(e.getUser()));
        return firmUsers;
    }

}
