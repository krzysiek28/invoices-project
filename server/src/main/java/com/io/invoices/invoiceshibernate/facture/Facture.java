package com.io.invoices.invoiceshibernate.facture;

import com.io.invoices.invoiceshibernate.bankAccount.BankAccount;
import com.io.invoices.invoiceshibernate.client.Client;
import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.productentry.ProductEntry;
import org.jsoup.Jsoup;

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
    @OneToOne
    private BankAccount bankAccount;
    @OneToMany(fetch = FetchType.EAGER)
    private List<ProductEntry> products;
    private String number;
    private String place;
    private Date issueDate;
    private Date paymentDate;
    private String issuer;
    private String paymentMethod;
    private Double paid;
    private Double toPay;
    private Double total;
    private String currency;

    public Facture() {

    }

    public Facture(Firm firm, Client client, BankAccount bankAccount, List<ProductEntry> products, String number, String place, Date issueDate, Date paymentDate, String issuer, String paymentMethod, Double paid, Double toPay, Double total, String currency) {

        this.firm = firm;
        this.client = client;
        this.bankAccount = bankAccount;
        this.products = products;
        this.number = number;
        this.place = place;
        this.issueDate = issueDate;
        this.paymentDate = paymentDate;
        this.issuer = issuer;
        this.paymentMethod = paymentMethod;
        this.paid = paid;
        this.toPay = toPay;
        this.total = total;
        this.currency = currency;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
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

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public Double getToPay() {
        return toPay;
    }

    public void setToPay(Double toPay) {
        this.toPay = toPay;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void stripTags() {
        this.setCurrency(Jsoup.parse(this.getCurrency()).text());
        this.setNumber(Jsoup.parse(this.getNumber()).text());
        this.setPaymentMethod(Jsoup.parse(this.getPaymentMethod()).text());
        this.setPlace(Jsoup.parse(this.getPlace()).text());
    }

    public boolean isCorrect() {
        if (this.getCurrency().isEmpty() || this.getNumber().isEmpty() || this.getPaymentMethod().isEmpty() || this.getPlace().isEmpty())
            return false;
        return true;
    }
}