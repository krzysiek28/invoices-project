package com.io.invoices.invoiceshibernate.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Users {

    @Id
    private String email;
    private String password;

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Users(){}

    public String getEmial() {
        return email;
    }

    public void setEmial(String emial) {
        this.email = emial;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
