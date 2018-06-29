package com.example.demo2.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@Table(name = "leave_requests")
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id_request;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "leave_request_type_id")
    private LeaveRequestType leaveRequestType;

    @NotNull
    private LocalDate startDay;

    @NotNull
    private LocalDate endDay;

    @NotNull
    long days;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public long getId() {
        return id_request;
    }

    public void setId(long id) {
        this.id_request = id;
    }

    public LeaveRequestType getRequestType() {
        return leaveRequestType;
    }

    public void setRequestType(LeaveRequestType requestType) {
        this.leaveRequestType = requestType;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
