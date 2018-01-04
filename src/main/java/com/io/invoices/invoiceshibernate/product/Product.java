package com.io.invoices.invoiceshibernate.product;

import com.io.invoices.invoiceshibernate.user.Usery;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Usery usery;
    private String name;

    public Product(){}

    public Product(Usery usery, String name) {
        this.usery = usery;
        this.name = name;
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
}

/*
    product_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    name VARCHAR NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (product_id, user_id)
*/
