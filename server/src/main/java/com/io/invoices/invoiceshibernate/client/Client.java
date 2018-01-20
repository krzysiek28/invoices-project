package com.io.invoices.invoiceshibernate.client;

import com.io.invoices.invoiceshibernate.firm.Firm;
import org.jsoup.Jsoup;

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

    public Client(Integer id, String name, String additionalData, Firm owner) {
        this.id = id;
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

    public Firm getOwner() {
        return owner;
    }

    public void setOwner(Firm owner) {
        this.owner = owner;
    }

    public boolean isCorrect() {
        if (this.getName().isEmpty() || this.getAdditionalData().isEmpty())
            return false;
        return true;
    }

    public void stripTags() {
        this.setName(Jsoup.parse(this.getName()).text());
        this.setAdditionalData(Jsoup.parse(this.getAdditionalData()).text());
    }
}
