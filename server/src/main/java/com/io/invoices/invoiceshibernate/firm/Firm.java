package com.io.invoices.invoiceshibernate.firm;

import com.io.invoices.invoiceshibernate.user.ApplicationUser;
import org.jsoup.Jsoup;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Firm {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String nip;
    private String place;
    private String phone;
    private String email;
    @ManyToOne
    private ApplicationUser owner;

    public Firm(String name, String nip, String place, String phone, String email, ApplicationUser owner) {
        this.name = name;
        this.nip = nip;
        this.place = place;
        this.phone = phone;
        this.email = email;
        this.owner = owner;
    }

    public Firm() {
    }

    public Firm(Integer id, String name, String nip, String phone, String place, String email, ApplicationUser owner) {
        this.id = id;
        this.name = name;
        this.nip = nip;
        this.place = place;
        this.phone = phone;
        this.email = email;
        this.owner = owner;
    }

    public Firm(String name, String nip, String place, String phone, String email) {
        this.name = name;
        this.nip = nip;
        this.place = place;
        this.phone = phone;
        this.email = email;
    }

    public ApplicationUser getOwner() {
        return owner;
    }

    public void setOwner(ApplicationUser owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void stripTags() {
        this.setPlace(Jsoup.parse(this.getPlace()).text());
        this.setPhone(Jsoup.parse(this.getPhone()).text());
        this.setNip(Jsoup.parse(this.getNip()).text());
        this.setName(Jsoup.parse(this.getName()).text());
        this.setEmail(Jsoup.parse(this.getEmail()).text());
    }

    public boolean isCorrect() {
        if (this.getPlace().isEmpty() || this.getPhone().isEmpty() || this.getNip().isEmpty() || this.getName().isEmpty() || this.getEmail().isEmpty())
            return false;
        return true;
    }
}

