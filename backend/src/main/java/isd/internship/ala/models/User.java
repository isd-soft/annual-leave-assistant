package isd.internship.ala.models;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") private Long id;
    @Column(name = "name") private String name;
    @Column(name = "surname") private String surname;
    @Column(name = "email") private String email;
    @Column(name = "password") private String password;
    @Column(name = "empDate") private LocalDate empDate;
    @Column(name = "role") private String role;

    public User(){}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String name, String surname, LocalDate empDate, String role){
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.empDate = empDate;
        this.role = role;
    }

    public User(String email, String password, String role){
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.empDate = user.getEmpDate();
        this.role = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getEmpDate() {
        return empDate;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmpDate(LocalDate empDate) {
        this.empDate = empDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

