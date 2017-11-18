package com.io.invoices.invoiceshibernate.facture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;

@Service
public class FactureService {
    @Autowired
    FactureRepository factureRepository;
}
