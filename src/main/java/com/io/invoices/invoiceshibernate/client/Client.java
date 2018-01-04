package com.io.invoices.invoiceshibernate.client;

import com.io.invoices.invoiceshibernate.user.Usery;

import javax.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String additionalData;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Usery owner;

    public Client() {
    }

    public Client(String name, String additionalData, Usery owner) {
        this.name = name;
        this.additionalData = additionalData;
        this.owner = owner;
    }

    //temp
    public Client(String name, String additionalData) {
        this.name = name;
        this.additionalData = additionalData;
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

    public Usery getOwner() {
        return owner;
    }

    public void setOwner(Usery owner) {
        this.owner = owner;
    }
}
