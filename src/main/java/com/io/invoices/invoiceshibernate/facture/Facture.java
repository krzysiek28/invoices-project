package com.io.invoices.invoiceshibernate.facture;

import com.io.invoices.invoiceshibernate.client.Client;
import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.productentry.ProductEntry;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Facture {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "firm_id", nullable = false)
    private Firm firm;
    @OneToOne
    private Client client;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductEntry> products;
    private String number;
    private String place;
    private Date issueDate;
    private Date paymentDate;
    private String issuer;
    private String paymentMethod;
    private Boolean paid;

    public Facture(Firm firm, Client client, List<ProductEntry> products, String number, String place, Date issueDate, Date paymentDate, String issuer, String paymentMethod, Boolean paid) {
        this.firm = firm;
        this.client = client;
        this.products = products;
        this.number = number;
        this.place = place;
        this.issueDate = issueDate;
        this.paymentDate = paymentDate;
        this.issuer = issuer;
        this.paymentMethod = paymentMethod;
        this.paid = paid;
    }

    public Facture() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ProductEntry> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntry> products) {
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
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
