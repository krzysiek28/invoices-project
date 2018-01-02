package com.io.invoices.invoiceshibernate.bankAccount;

import com.io.invoices.invoiceshibernate.user.Users;

import javax.persistence.*;

@Entity
public class BankAccount {
    @Id
    private String bankAccount;
    @ManyToOne
    private Users users;

    BankAccount(){}

    public BankAccount(String bankAccount, Users users) {
        this.bankAccount = bankAccount;
        this.users = users;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
