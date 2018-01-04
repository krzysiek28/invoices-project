package com.io.invoices.invoiceshibernate.facture;

import com.io.invoices.invoiceshibernate.client.Client;
import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.product.Product;
import com.io.invoices.invoiceshibernate.user.Usery;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Facture {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usery usery;
    @OneToOne
    private Firm userFirm;
    @OneToOne
    private Client client;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;
    private String number;
    private String place;
    private Date issueDate;
    private Date paymentDate;
    private String issuer;

    public Facture(Usery usery, Firm userFirm, Client client, List<Product> products, String number, String place, Date issueDate, Date paymentDate, String issuer) {
        this.usery = usery;
        this.userFirm = userFirm;
        this.client = client;
        this.products = products;
        this.number = number;
        this.place = place;
        this.issueDate = issueDate;
        this.paymentDate = paymentDate;
        this.issuer = issuer;
    }

    public Facture() {
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

    public Firm getUserFirm() {
        return userFirm;
    }

    public void setUserFirm(Firm userFirm) {
        this.userFirm = userFirm;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }


}

/*
    facture_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    firm_id INTEGER NOT NULL,
    user_firm_id INTEGER NOT NULL,
    client_id INTEGER NOT NULL,
    name VARCHAR NOT NULL,
    place VARCHAR NOT NULL,
    date DATE NOT NULL,
    price numeric(7,2) NOT NULL,
    CONSTRAINT facture_pk PRIMARY KEY (facture_id, user_id)*/
