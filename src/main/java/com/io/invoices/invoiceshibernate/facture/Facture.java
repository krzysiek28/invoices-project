package com.io.invoices.invoiceshibernate.facture;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Facture {
    @Id
    @GeneratedValue
    private Integer id;
    //private
}

/*
    facture_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    firm_id INTEGER NOT NULL,
    user_firm_id INTEGER NOT NULL,
    client_id INTEGER NOT NULL,
    name VARCHAR NOT NULL,
    place VARCHAR NOT NULL,
    date DATE NOT NULL,
    price numeric(7,2) NOT NULL,
    CONSTRAINT facture_pk PRIMARY KEY (facture_id, user_id)*/
