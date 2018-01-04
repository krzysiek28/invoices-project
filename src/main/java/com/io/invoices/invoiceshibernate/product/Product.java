package com.io.invoices.invoiceshibernate.product;

import com.io.invoices.invoiceshibernate.user.Usery;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usery usery;
    private String name;
    private Float netUnitPrice;
    private Float vatRate;
    private String unit;

    public Product(Usery usery, String name, Float netUnitPrice, Float vatRate, String unit) {
        this.usery = usery;
        this.name = name;
        this.netUnitPrice = netUnitPrice;
        this.vatRate = vatRate;
        this.unit = unit;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usery getUsery() {
        return usery;
    }

    public void setUsery(Usery usery) {
        this.usery = usery;
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


}

/*
    product_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    name VARCHAR NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (product_id, user_id)
*/
