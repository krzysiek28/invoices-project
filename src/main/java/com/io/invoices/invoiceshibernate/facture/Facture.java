package com.io.invoices.invoiceshibernate.facture;

import com.io.invoices.invoiceshibernate.client.Client;
import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.user.User;

import javax.persistence.*;

@Entity
public class Facture {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @OneToOne
    private Firm userFirm;
    @OneToOne
    private Client client;
    private String name;
    private String place;
    private Float price;

    public Facture(){}

    public Facture(User user, Firm userFirm, Client client, String name, String place, Float price) {
        this.user = user;
        this.userFirm = userFirm;
        this.client = client;
        this.name = name;
        this.place = place;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Firm getUserFirm() {
        return userFirm;
    }

    public void setUserFirm(Firm userFirm) {
        this.userFirm = userFirm;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
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
