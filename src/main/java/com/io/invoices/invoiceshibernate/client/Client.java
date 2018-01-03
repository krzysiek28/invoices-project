package com.io.invoices.invoiceshibernate.client;

import com.io.invoices.invoiceshibernate.user.User;

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
    private User owner;

    public Client(String name, String additionalData, User owner) {
        this.name = name;
        this.additionalData = additionalData;
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

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
