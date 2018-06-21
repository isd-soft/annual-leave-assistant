package com.isd.annual_leave_assistant.model;


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

    @Column(name = "LEAVE_REQUEST_TYPE_ID")
    private Integer leaveRequestTypeId;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "STATUS_ID")
    private Integer statusId;

    @Column(name = "REQUEST_DATE")
    private Date requestDate;

    public LeaveRequest() {
    }

    public LeaveRequest(Integer leaveRequestTypeId, Integer userId, Date startDate, Date endDate, Integer statusId, Date requestDate) {
        this.leaveRequestTypeId = leaveRequestTypeId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.statusId = statusId;
        this.requestDate = requestDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeaveRequestTypeId() {
        return leaveRequestTypeId;
    }

    public void setLeaveRequestTypeId(Integer leaveRequestTypeId) {
        this.leaveRequestTypeId = leaveRequestTypeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}
