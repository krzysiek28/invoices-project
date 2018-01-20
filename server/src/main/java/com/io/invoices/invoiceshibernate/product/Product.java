package com.io.invoices.invoiceshibernate.product;

import com.io.invoices.invoiceshibernate.firm.Firm;
import org.jsoup.Jsoup;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "firm_id", nullable = true)
    private Firm owner;
    private String name;
    private Float netUnitPrice;
    private Float vatRate;
    private String unit;
    private String currency;

    public Product() {
    }

    public Product(Integer id, Firm owner, String name, Float netUnitPrice, Float vatRate, String unit) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.netUnitPrice = netUnitPrice;
        this.vatRate = vatRate;
        this.unit = unit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Firm getOwner() {
        return owner;
    }

    public void setOwner(Firm owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getNetUnitPrice() {
        return netUnitPrice;
    }

    public void setNetUnitPrice(Float netUnitPrice) {
        this.netUnitPrice = netUnitPrice;
    }

    public Float getVatRate() {
        return vatRate;
    }

    public void setVatRate(Float vatRate) {
        this.vatRate = vatRate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void stipTags() {
        this.setName(Jsoup.parse(this.getName()).text());
        this.setCurrency(Jsoup.parse(this.getCurrency()).text());
        this.setUnit(Jsoup.parse(this.getUnit()).text());
    }

    public boolean isCorrect() {
        if (this.getName().isEmpty() || this.getCurrency().isEmpty() || this.getUnit().isEmpty() || (this.getNetUnitPrice() < 0) || (this.getVatRate() < 0) || (this.getVatRate() > 100))
            return false;
        return true;
    }

}