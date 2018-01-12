package com.io.invoices.invoiceshibernate.client;

import com.io.invoices.invoiceshibernate.firm.Firm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String additionalData;
    @ManyToOne
    private Firm owner;

    public Client(String name, String additionalData, Firm owner) {
        this.name = name;
        this.additionalData = additionalData;
        this.owner = owner;
    }

    public Client() {
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

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public Firm getOwner() {
        return owner;
    }

    public void setOwner(Firm owner) {
        this.owner = owner;
    }

}
