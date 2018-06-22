package com.isd.leaveassistant.service;

import com.isd.leaveassistant.LeaveRequestMaximumDays;
import com.isd.leaveassistant.model.LeaveRequest;
import com.isd.leaveassistant.repository.LeaveRequestRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Override
    public List<LeaveRequest> getAll() {
        return leaveRequestRepository.findAll();
    }

    @Override
    public List<LeaveRequest> findAllByUserId(Integer id) {
        return leaveRequestRepository.findAllByUserIdEqualsOrderByRequestDate(id);
    }

    @Override
    public List<LeaveRequest> findAllPending(Integer statusId) {
        return leaveRequestRepository.findAllByStatusIdEqualsOrderByRequestDate(statusId);
    }

    @Override
    public List<LeaveRequest> findAllPendingByUserId(Integer userId, Integer statusId) {
        return leaveRequestRepository.findAllByUserIdEqualsAndStatusIdEquals(userId, statusId);
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
        leaveRequestNew.setLeaveRequestTypeId(leaveRequest.getLeaveRequestTypeId());
        leaveRequestNew.setUserId(leaveRequest.getUserId());
        leaveRequestNew.setRequestDate(leaveRequest.getRequestDate());
        leaveRequestNew.setStartDate(leaveRequest.getStartDate());
        leaveRequestNew.setEndDate(leaveRequest.getEndDate());
        leaveRequestNew.setStatusId(leaveRequest.getStatusId());
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
        Date daysDiff = new Date(startDate.getTime()- endDate.getTime());
        Integer totalDaysOff = Integer.parseInt(daysDiff.toString())/24/60/60/1000 + 1;
        return totalDaysOff;
    }

    public boolean CheckAvailableDays (LeaveRequest leaveRequest) {
        List<LeaveRequest> totalLeaveRequests = leaveRequestRepository.findAllByUserIdEqualsAndStatusIdEquals(leaveRequest.getUserId(), 2);
        Integer totalDaysOff = 0;
        Integer MaximumDays = LeaveRequestMaximumDays.MaximumDaysAvailable;
        DateTime date = new DateTime();
        LocalDate dateNow = LocalDate.now();
        //LocalDate date = LocalDate.of(dateNow.getYear(), 1, 1);
        for (LeaveRequest item :totalLeaveRequests) {
            if(item.getStartDate().getTime() > date. )
            totalDaysOff += DateDiff(item.getStartDate(),item.getEndDate());
        }
        Integer currentDaysOff = DateDiff(leaveRequest.getStartDate(), leaveRequest.getEndDate())
        if (totalDaysOff<MaximumDays && MaximumDays - totalDaysOff <=currentDaysOff) {
            return true;
        } else {
            return false;
        }
    }
    public boolean CheckFourTeenDays (LeaveRequest leaveRequest) {
        List<LeaveRequest> totalLeaveRequests = leaveRequestRepository.findAllByUserIdEqualsAndStatusIdEquals(leaveRequest.getUserId(), 2);
        Integer totalDaysOff = 0;
        Integer MaximumDays = LeaveRequestMaximumDays.MaximumDaysAvailable;
        LocalDate dateNow = LocalDate.now();

        for (LeaveRequest item :totalLeaveRequests) {
            if(DateDiff(item.getStartDate(),item.getEndDate()) ==14)
            return true
        }
        Integer currentDaysOff = DateDiff(leaveRequest.getStartDate(), leaveRequest.getEndDate())
        if (totalDaysOff<MaximumDays && MaximumDays - totalDaysOff <=currentDaysOff) {
            return true;
        } else {
            return false;
        }
    }


}

