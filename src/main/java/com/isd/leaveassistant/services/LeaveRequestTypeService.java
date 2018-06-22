package com.isd.leaveassistant.services;

import com.isd.leaveassistant.model.LeaveRequestType;

import java.util.List;

public interface LeaveRequestTypeService {

    List<LeaveRequestType> getAll();
    LeaveRequestType getById(Integer id);
    LeaveRequestType create(LeaveRequestType leaveRequestType);
    LeaveRequestType update(LeaveRequestType leaveRequestType, Integer id);
    void deleteById(Integer id);
    void deleteAll();

}
