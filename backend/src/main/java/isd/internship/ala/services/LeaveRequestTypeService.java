package isd.internship.ala.services;

import isd.internship.ala.models.LeaveRequestType;

import java.util.List;

public interface LeaveRequestTypeService {
    List<LeaveRequestType> getAll();
    LeaveRequestType getById(Integer id);
    LeaveRequestType create(LeaveRequestType leaveRequestType);
    LeaveRequestType update(LeaveRequestType leaveRequestType, Integer id);
    void deleteById(Integer id);
    void deleteAll();
    LeaveRequestType findByName(String name);
}
