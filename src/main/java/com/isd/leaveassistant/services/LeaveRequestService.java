package com.isd.leaveassistant.services;

import com.isd.leaveassistant.model.LeaveRequest;
import com.isd.leaveassistant.model.Status;
import com.isd.leaveassistant.model.User;

import java.util.List;

public interface LeaveRequestService {

    List<LeaveRequest> getAll();
    List<LeaveRequest> findAllByUserId(User id);
    List<LeaveRequest> findAllPending (Status statusId);
    List<LeaveRequest> findAllPendingByUserId (User userId, Status statusId );
    LeaveRequest getById(Integer id);
    LeaveRequest create(LeaveRequest leaveRequest);
    LeaveRequest update(LeaveRequest leaveRequest, Integer id);
    void deleteById(Integer id);
    void deleteAll();

}
