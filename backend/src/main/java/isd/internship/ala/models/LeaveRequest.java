package isd.internship.ala.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "LEAVE_REQUESTS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LeaveRequest {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "LEAVE_REQUEST_TYPE_FK")
    private LeaveRequestType leaveRequestType;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "USER_FK")
    private User user;

    @Column(name = "START_DATE")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;


    @Column(name = "END_DATE")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "STATUS_FK", nullable = false)
    private Status status;

    @Column(name = "REQUEST_DATE")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate requestDate;

    public LeaveRequest() {
    }

    public LeaveRequest(LeaveRequestType leaveRequestType, User user, LocalDate startDate, LocalDate endDate, Status status, LocalDate requestDate) {
        this.leaveRequestType = leaveRequestType;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.requestDate = requestDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LeaveRequestType getLeaveRequestType() {
        return leaveRequestType;
    }

    public void setLeaveRequestType(LeaveRequestType leaveRequestType) {
        this.leaveRequestType = leaveRequestType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }
}
