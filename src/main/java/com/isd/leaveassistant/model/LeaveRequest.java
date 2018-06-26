package com.isd.leaveassistant.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
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
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "STATUS_FK", nullable = false)
    private Status status;

    @Column(name = "REQUEST_DATE")
    private Date requestDate;

    public LeaveRequest() {
    }

    public LeaveRequest(LeaveRequestType leaveRequestType, User user, Date startDate, Date endDate, Status status, Date requestDate) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}
