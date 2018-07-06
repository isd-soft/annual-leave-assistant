package isd.internship.ala.models;


import javax.persistence.*;

@Entity
@Table(name = "LEAVE_REQUEST_TYPES")
public class LeaveRequestType {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "description")
    private String description;

    public LeaveRequestType() {
    }

    public LeaveRequestType(String name) {
        this.name = name;
    }

    public LeaveRequestType(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
