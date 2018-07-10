package isd.internship.ala.services;

import isd.internship.ala.models.LeaveRequest;
import isd.internship.ala.models.LeaveRequestType;
import isd.internship.ala.models.Status;
import isd.internship.ala.models.User;

import java.util.HashMap;
import java.util.List;

public interface LeaveRequestService {
//    List<LeaveRequest> getAll();
//    List<LeaveRequest> findAllByUserId(User id);
//    List<LeaveRequest> findAllPending (Status statusId);
//    List<LeaveRequest> findAllPendingByUserId (User userId, Status statusId );
//    LeaveRequest getById(Integer id);
    LeaveRequest create(LeaveRequest leaveRequest);
//    int getTotalDays(Long user_id, Integer year);
    boolean taked14days(Long user_id, Integer year);
    boolean alreadyRequested(LeaveRequest leaveRequest);
    List<HashMap<String, String>> getByUserId(Long id);
    List<HashMap<String, String>> getAll();
    String check(LeaveRequest leaveRequest, LeaveRequestType type, User foundUser);
//    LeaveRequest update(LeaveRequest leaveRequest, Integer id);
//    void deleteById(Integer id);
//    void deleteAll();

}
