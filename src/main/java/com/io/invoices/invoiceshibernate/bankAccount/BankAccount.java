package com.io.invoices.invoiceshibernate.bankAccount;

import com.io.invoices.invoiceshibernate.user.User;

import javax.persistence.*;

@Entity
public class BankAccount {
    @Id
    private String bankAccount;
    @ManyToOne
    private User user;

    BankAccount(){}

    public BankAccount(String bankAccount, User user) {
        this.bankAccount = bankAccount;
        this.user = user;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
