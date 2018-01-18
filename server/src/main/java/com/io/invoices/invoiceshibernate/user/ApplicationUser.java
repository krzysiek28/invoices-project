package com.io.invoices.invoiceshibernate.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "usery")
public class ApplicationUser {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(unique = true)
    private String email;
    @JsonIgnoreProperties
    private String role;
    private String personalData;
    private Boolean enabled;

    /**
     * constructor creates ApplicationUser object
     * @param username
     * @param password
     * @param email
     * @param role param which spring security requires
     * @param personalData
     * @param enabled param which spring security requires
     */
    public ApplicationUser(String username, String password, String email, String role, String personalData, Boolean enabled) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.personalData = personalData;
        this.enabled = enabled;
    }

    public ApplicationUser() {
    }


    public ApplicationUser(Integer id, String username, String password, String email, String role, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.enabled = enabled;
    }

    public String getPersonalData() {
        return personalData;
    }

    public void setPersonalData(String personalData) {
        this.personalData = personalData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
