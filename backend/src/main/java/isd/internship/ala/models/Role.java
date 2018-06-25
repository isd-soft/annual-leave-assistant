package isd.internship.ala.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id") private int roleId;
    @Column(name = "role") private String role;

    public void setId(int id) {
        this.roleId = id;
    }

    public int getId() {
        return roleId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
