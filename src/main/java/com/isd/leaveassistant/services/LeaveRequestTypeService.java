package com.isd.annual_leave_assistant.service;

import com.isd.annual_leave_assistant.model.LeaveRequestType;

import java.util.List;

public interface LeaveRequestTypeService {

    List<LeaveRequestType> getAll();
    LeaveRequestType getById(Integer id);
    LeaveRequestType create(LeaveRequestType leaveRequestType);
    LeaveRequestType update(LeaveRequestType leaveRequestType, Integer id);
    void deleteById(Integer id);
    void deleteAll();

}
