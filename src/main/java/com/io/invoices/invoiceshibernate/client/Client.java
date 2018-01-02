package com.io.invoices.invoiceshibernate.client;

import com.io.invoices.invoiceshibernate.firm.Firm;
import com.io.invoices.invoiceshibernate.user.Users;

import javax.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Users users;
    @OneToOne(fetch = FetchType.LAZY)
    private Firm firm;

    public Client(){}

    public Client(Users users, Firm firm) {
        this.users = users;
        this.firm = firm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }
}

/*
        client_id INTEGER NOT NULL,
        user_id INTEGER NOT NULL,
        firm_id INTEGER NOT NULL,
        CONSTRAINT client_pk PRIMARY KEY (client_id, user_id, firm_id)
*/
