package web.mvc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class FirmUsers {
    private Integer id;
    private Usery user;
    private Firm firm;

    public FirmUsers(){}

    public FirmUsers(Usery user, Firm firm) {
        this.user = user;
        this.firm = firm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usery getUser() {
        return user;
    }

    public void setUser(Usery user) {
        this.user = user;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }
}

/*
        user_firm_id INTEGER NOT NULL,
        user_id INTEGER NOT NULL,
        firm_id INTEGER NOT NULL,
        CONSTRAINT user_firm_pk PRIMARY KEY (user_firm_id, user_id, firm_id)*/
