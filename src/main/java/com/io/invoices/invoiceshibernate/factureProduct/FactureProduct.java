package com.io.invoices.invoiceshibernate.factureProduct;

import com.io.invoices.invoiceshibernate.facture.Facture;
import com.io.invoices.invoiceshibernate.product.Product;
import com.io.invoices.invoiceshibernate.user.Users;

import javax.persistence.*;

@Entity
public class FactureProduct {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name="facture_id", nullable=false)
    private Facture facture;
    @OneToOne(fetch = FetchType.LAZY)
    private Users users;
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    public FactureProduct(){}

    public FactureProduct(Facture facture, Users users, Product product) {
        this.facture = facture;
        this.users = users;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

/*
    facture_product_id INTEGER NOT NULL,
    facture_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    CONSTRAINT facture_product_pk PRIMARY KEY (facture_product_id, facture_id, user_id)*/
