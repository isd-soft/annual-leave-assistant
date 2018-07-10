package isd.internship.ala.services;

import isd.internship.ala.models.LeaveRequestType;

import java.util.List;

public interface LeaveRequestTypeService {
    List<LeaveRequestType> getAll();
    LeaveRequestType getById(int id);
    LeaveRequestType create(LeaveRequestType leaveRequestType);
    LeaveRequestType update(int id, LeaveRequestType leaveRequestType);
    void deleteById(int id);
    void deleteAll();
    LeaveRequestType findByName(String name);
}
