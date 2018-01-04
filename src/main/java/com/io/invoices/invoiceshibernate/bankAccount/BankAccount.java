package com.io.invoices.invoiceshibernate.bankAccount;

import com.io.invoices.invoiceshibernate.user.Usery;

import javax.persistence.*;

@Entity
public class BankAccount {
    @Id
    private String bankAccount;
    @ManyToOne
    private Usery usery;

    BankAccount(){}

    public BankAccount(String bankAccount, Usery usery) {
        this.bankAccount = bankAccount;
        this.usery = usery;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Usery getUsery() {
        return usery;
    }

    public void setUsery(Usery usery) {
        this.usery = usery;
    }
}
