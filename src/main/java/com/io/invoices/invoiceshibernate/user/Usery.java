package com.io.invoices.invoiceshibernate.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Usery {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    private String role;
    private Boolean enabled;

    public Usery(Integer id, String name, String email, String password, String role, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public Usery(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Usery() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
