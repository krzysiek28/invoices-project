package web.mvc.domain;

import java.sql.Date;
import java.util.List;

public class Facture {
    private Integer id;
    private Firm firm;
    private Client client;
    private BankAccount bankAccount;
    private List<ProductEntry> products;
    private String number;
    private String place;
    private Date issueDate;
    private Date paymentDate;
    private String issuer;
    private String paymentMethod;
    private Float paid;
    private Float toPay;
    private Float total;
    private String currency;

    public Facture(Integer id, Firm firm, Client client, BankAccount bankAccount, List<ProductEntry> products, String number, String place, Date issueDate, Date paymentDate, String issuer, String paymentMethod, Float paid, Float toPay, Float total, String currency) {
        this.id = id;
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

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
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

    public Float getPaid() {
        return paid;
    }

    public void setPaid(Float paid) {
        this.paid = paid;
    }

    public Float getToPay() {
        return toPay;
    }

    public void setToPay(Float toPay) {
        this.toPay = toPay;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}