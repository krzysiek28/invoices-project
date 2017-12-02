package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.firm.FirmService;
import com.io.invoices.invoiceshibernate.firmUsers.FirmUsers;
import com.io.invoices.invoiceshibernate.firmUsers.FirmUsersService;
import com.io.invoices.invoiceshibernate.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FirmUsersController {

    @Autowired
    private FirmUsersService firmUsersService;

    @Autowired
    private FirmService firmService;


    @GetMapping (value = "/{name}")
    public List<User> getUsersByFirmName(@PathVariable String name){
        return firmService.findFirmUsersByFirm_Name(name);
    }
}