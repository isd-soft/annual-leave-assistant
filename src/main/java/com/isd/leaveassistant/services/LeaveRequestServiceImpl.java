package com.isd.leaveassistant.services;

import com.isd.leaveassistant.LeaveRequestMaximumDays;
import com.isd.leaveassistant.model.LeaveRequest;
import com.isd.leaveassistant.model.Status;
import com.isd.leaveassistant.model.User;
import com.isd.leaveassistant.repositories.LeaveRequestRepository;
import com.isd.leaveassistant.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class LeaveRequestServiceImpl implements LeaveRequestService, LeaveRequestMaximumDays {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    private StatusRepository statusRepository;

    @Override
    public List<LeaveRequest> getAll() {
        return leaveRequestRepository.findAll();
    }

    @Override
    public List<LeaveRequest> findAllByUserId(User id) {
        return leaveRequestRepository.findAllByUserIdEqualsOrderByRequestDate(id);
    }

    @Override
    public List<LeaveRequest> findAllPending(Status statusId) {
        return leaveRequestRepository.findAllByStatusIdEqualsOrderByRequestDate(statusId);
    }

    @Override
    public List<LeaveRequest> findAllPendingByUserId(User userId, Status statusId) {
        return leaveRequestRepository.findAllByUserIdEqualsAndStatusIdEquals(userId, statusRepository.getOne(1));
    }

    @Override
    public LeaveRequest getById(Integer id) { return leaveRequestRepository.getOne(id); }

    @Override
    public LeaveRequest create(LeaveRequest leaveRequest) {

        if (CheckAvailableDays(leaveRequest)){
            return leaveRequestRepository.save(leaveRequest);
        } else {
            return null;
        }
    }

    @Override
    public LeaveRequest update(LeaveRequest leaveRequest, Integer id) {
        LeaveRequest leaveRequestNew = leaveRequestRepository.getOne(id);
        leaveRequestNew.setLeaveRequestType(leaveRequest.getLeaveRequestType());
        leaveRequestNew.setUser(leaveRequest.getUser());
        leaveRequestNew.setRequestDate(leaveRequest.getRequestDate());
        leaveRequestNew.setStartDate(leaveRequest.getStartDate());
        leaveRequestNew.setEndDate(leaveRequest.getEndDate());
        leaveRequestNew.setStatus(leaveRequest.getStatus());
        leaveRequestRepository.save(leaveRequestNew);
        return leaveRequestNew;
    }

    @Override
    public void deleteById(Integer id) {
        leaveRequestRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        leaveRequestRepository.deleteAll();
    }

    public Integer DateDiff (Date startDate, Date endDate) {
        LocalDate dateFrom = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateTo = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Period intervalPeriod = Period.between(dateFrom, dateTo);
        if(intervalPeriod.getMonths()==0 && intervalPeriod.getYears()==0) {
            return intervalPeriod.getDays()+1;
        }else {
            return 0;
        }
    }

    public boolean CheckAvailableDays (LeaveRequest leaveRequest) {
        List<LeaveRequest> totalLeaveRequests = leaveRequestRepository.findAllByUserIdEqualsAndStatusIdEquals(leaveRequest.getUser(),statusRepository.getOne(2));
        Integer totalDaysOff = 0;
        Integer MaximumDays = MaximumDaysAvailable;
        LocalDate dateNow = LocalDate.now();
        LocalDate date = LocalDate.of(dateNow.getYear()-1, 12, 31);
        for (LeaveRequest item :totalLeaveRequests) {
            if(item.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(date)) {
                totalDaysOff += DateDiff(item.getStartDate(), item.getEndDate());
            }
        }
        Integer currentDaysOff = DateDiff(leaveRequest.getStartDate(), leaveRequest.getEndDate());
        if (totalDaysOff<MaximumDays && MaximumDays - totalDaysOff >=currentDaysOff) {
            return true;
        } else {
            return false;
        }
    }
    public boolean CheckFourTeenDays (LeaveRequest leaveRequest) {
        List<LeaveRequest> totalLeaveRequests = leaveRequestRepository.findAllByUserIdEqualsAndStatusIdEquals(leaveRequest.getUser(), statusRepository.getOne(2));
        Integer totalDaysOff = 0;
        Integer MaximumDays = MaximumDaysAvailable;
        LocalDate dateNow = LocalDate.now();
        LocalDate date = LocalDate.of(dateNow.getYear()-1, 12, 31);

        List <Integer> leaves = new ArrayList<>();

        for (LeaveRequest item :totalLeaveRequests) {
            if(item.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(date)) {
                leaves.add(DateDiff(item.getStartDate(),item.getEndDate())+1);
            }
        }


        boolean moreThan14 = Fourteen <= leaves.stream().filter(v-> v>14).findAny().get();
        if (moreThan14){
            return true;
        }else{
            return false;
        }
    }


}

