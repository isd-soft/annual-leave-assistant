package com.isd.annual_leave_assistant.service;

import com.isd.annual_leave_assistant.model.LeaveRequest;
import com.isd.annual_leave_assistant.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<LeaveRequest> findAllPending() {
        return null;
    }

    @Override
    public LeaveRequest getById(Integer id) { return leaveRequestRepository.getOne(id); }

    @Override
    public LeaveRequest create(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
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
}
