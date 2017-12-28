package com.io.invoices.invoiceshibernate.controllers;

import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.firm.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/firm")
public class FirmController {

    @Autowired
    private FirmService firmService;

    @GetMapping (value = "/firms")
    public List<Firm> getFirms(){
        return firmService.getFirms();
    }



}
