package com.io.invoices.invoiceshibernate.firm;

import com.io.invoices.invoiceshibernate.user.Usery;

import javax.persistence.*;

@Entity
public class Firm {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String nip;
    private String place;
    private String phone;
    private String email;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Usery owner;

    public Firm() {
    }

    public Firm(String name, String nip, String place, String phone, String email) {
        this.name = name;
        this.nip = nip;
        this.place = place;
        this.phone = phone;
        this.email = email;
    }

    public Usery getOwner() {
        return owner;
    }

    public void setOwner(Usery owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
/*
    firm_id INTEGER NOT NULL,
    name VARCHAR NOT NULL,
    NIP VARCHAR NOT NULL,
    place VARCHAR NOT NULL,
    phone VARCHAR,
    email VARCHAR,
    CONSTRAINT firm_pk PRIMARY KEY (firm_id)*/
