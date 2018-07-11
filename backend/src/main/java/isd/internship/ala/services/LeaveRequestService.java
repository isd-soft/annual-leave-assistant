package isd.internship.ala.services;

import isd.internship.ala.models.LeaveRequest;
import isd.internship.ala.models.LeaveRequestType;
import isd.internship.ala.models.Status;
import isd.internship.ala.models.User;

import java.util.HashMap;
import java.util.List;

public interface LeaveRequestService {
    LeaveRequest create(LeaveRequest leaveRequest);
    int getTotalDays(User user, Integer year, String type);
    boolean taked14days(Long user_id, Integer year);
    boolean alreadyRequested(LeaveRequest leaveRequest, User user);
    List<HashMap<String, String>> getByUserId(Long id);
    List<HashMap<String, String>> getAll();
    String check(LeaveRequest leaveRequest, LeaveRequestType type, User foundUser);
    void checkForHoliday(LeaveRequest leaveRequest);
}
