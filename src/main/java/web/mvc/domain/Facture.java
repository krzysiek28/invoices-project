package web.mvc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Facture {
    private Integer id;
    private Usery user;
    private Firm firm;
    private FirmUsers firmUsers;
    private Client client;
    private String name;
    private String place;
    private Float price;

    public Facture(){}

    public Facture(Usery user, Firm firm, FirmUsers firmUsers, Client client, String name, String place, Float price) {
        this.user = user;
        this.firm = firm;
        this.firmUsers = firmUsers;
        this.client = client;
        this.name = name;
        this.place = place;
        this.price = price;
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

    public FirmUsers getFirmUsers() {
        return firmUsers;
    }

    public void setFirmUsers(FirmUsers firmUsers) {
        this.firmUsers = firmUsers;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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
