package com.io.invoices.invoiceshibernate.factureProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactureProductService {
    @Autowired
    FactureProductRepository factureProductRepository;
}
