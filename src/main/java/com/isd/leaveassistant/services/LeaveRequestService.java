package com.isd.leaveassistant.service;

import com.isd.leaveassistant.model.LeaveRequest;

import java.util.List;

public interface LeaveRequestService {

    List<LeaveRequest> getAll();
    List<LeaveRequest> findAllByUserId(Integer id);
    List<LeaveRequest> findAllPending (Integer statusId);
    List<LeaveRequest> findAllPendingByUserId (Integer userId, Integer statusId );
    LeaveRequest getById(Integer id);
    LeaveRequest create(LeaveRequest leaveRequest);
    LeaveRequest update(LeaveRequest leaveRequest, Integer id);
    void deleteById(Integer id);
    void deleteAll();

}
