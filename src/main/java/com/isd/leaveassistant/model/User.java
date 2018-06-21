package com.isd.leaveassistant.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String surname;
    private String email;
    private String password;
    private Date employment_date;
    private int active;

    public User() {
    }

    public User(String name, String surname, String email, String password, Date employment_date, int active) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.employment_date = employment_date;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public Date getEmployment_date() {
        return employment_date;
    }

    public void setEmployment_date(Date employment_date) {
        this.employment_date = employment_date;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}

