package com.io.invoices.invoiceshibernate.bankAccount;

import com.io.invoices.invoiceshibernate.firm.Firm;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BankAccount {
    @Id
    private String bankAccount;
    private String additionalData;
    @ManyToOne
    private Firm firm;
    public BankAccount(String bankAccount, String additionalData, Firm firm) {
        this.bankAccount = bankAccount;
        this.additionalData = additionalData;
        this.firm = firm;
    }

    public BankAccount() {

    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }
}
