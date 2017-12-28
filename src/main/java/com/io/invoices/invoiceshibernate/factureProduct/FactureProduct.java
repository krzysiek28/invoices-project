package com.io.invoices.invoiceshibernate.factureProduct;

import com.io.invoices.invoiceshibernate.facture.Facture;
import com.io.invoices.invoiceshibernate.product.Product;
import com.io.invoices.invoiceshibernate.user.User;
import org.springframework.data.repository.cdi.Eager;

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
    private Product product;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public FactureProduct(){}

    public FactureProduct(Facture facture, Product product, User user) {
        this.facture = facture;
        this.product = product;
        this.user = user;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

/*
    facture_product_id INTEGER NOT NULL,
    facture_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    CONSTRAINT facture_product_pk PRIMARY KEY (facture_product_id, facture_id, user_id)*/
