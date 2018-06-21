package com.isd.annual_leave_assistant.service;

import com.isd.annual_leave_assistant.model.LeaveRequest;

import java.util.List;

public interface LeaveRequestService {

    List<LeaveRequest> getAll();
    List<LeaveRequest> findAllByUserId(Integer id);
    List<LeaveRequest> findAllPending ();
    LeaveRequest getById(Integer id);
    LeaveRequest create(LeaveRequest leaveRequest);
    LeaveRequest update(LeaveRequest leaveRequest, Integer id);
    void deleteById(Integer id);
    void deleteAll();

}
