package isd.internship.ala.services.impl;

import isd.internship.ala.models.LeaveRequest;
import isd.internship.ala.models.Status;
import isd.internship.ala.models.User;
import isd.internship.ala.repositories.LeaveRequestRepository;
import isd.internship.ala.repositories.StatusRepository;
import isd.internship.ala.services.LeaveRequestMaximumDays;
import isd.internship.ala.services.LeaveRequestService;
import isd.internship.ala.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService, LeaveRequestMaximumDays {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    private StatusRepository statusRepository;
    private UserService userService;

    @Override
    public LeaveRequest create(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }


//    @Override
//    public int getTotalDays(Long user_id, Integer year){
//        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();
//        int totalDays = 0;
//        for (LeaveRequest leaveRequest : leaveRequests) {
//            if (leaveRequest.getUser().getId().equals(user_id) &&
//                    (leaveRequest.getStartDate().getYear() == leaveRequest.getEndDate().getYear()) &&
//                    (leaveRequest.getStartDate().getYear() == year))
//                totalDays += Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getDays();
//        }
//        return totalDays;
//    }

    @Override
    public boolean taked14days(Long user_id, Integer year){
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();
        for (LeaveRequest leaveRequest : leaveRequests) {
            if (leaveRequest.getUser().getId().equals(user_id) &&
                    (leaveRequest.getStartDate().getYear() == leaveRequest.getEndDate().getYear()) &&
                    (leaveRequest.getStartDate().getYear() == year) &&
                    (Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getDays() + 1 == 14))
                return true;
        }
        return false;
    }

    @Override
    public boolean alreadyRequested(LeaveRequest leaveRequest){
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();
        LocalDate startDate = leaveRequest.getStartDate();
        LocalDate endDate = leaveRequest.getEndDate();
        for(LeaveRequest lr: leaveRequests) {
            if(lr.getUser().equals(leaveRequest.getUser()) && ( lr.getStartDate().equals(startDate) ||
                                                                lr.getEndDate().equals(endDate)) ||
                                                                lr.getStartDate().equals(endDate) ||
                                                                lr.getEndDate().equals(startDate) ||
                    (startDate.isBefore(lr.getEndDate()) && startDate.isAfter(lr.getStartDate())) ||
                    (endDate.isBefore(lr.getEndDate()) && endDate   .isAfter(lr.getStartDate()))){
                return true;
            }
        }
        return false;
    }




    @Override
    public List<HashMap<String, String>> getByUserId(Long id){
        List<HashMap<String, String>> result = new ArrayList<>();
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();

        for(LeaveRequest leaveRequest: leaveRequests){
            if(leaveRequest.getUser().getId().equals(id)){
                HashMap<String, String> element = new HashMap<>();
                element.put("id", leaveRequest.getId().toString());
                element.put("leaveRequestType", leaveRequest.getLeaveRequestType().getName());
                element.put("leaveRequestTypeId", leaveRequest.getLeaveRequestType().getId().toString());
                element.put("user", leaveRequest.getUser().getSurname() + " " + leaveRequest.getUser().getName());
                element.put("startDate", leaveRequest.getStartDate().toString());
                element.put("endDate", leaveRequest.getEndDate().toString());
                element.put("period", String.valueOf(Period.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()).getDays() + 1));
                element.put("status", leaveRequest.getStatus().getName());
                element.put("requestDate", leaveRequest.getRequestDate().toString());
                result.add(element);
                System.out.println(element);
            }
        }
        return result;
    }

    @Override
    public List<HashMap<String, String>> getAll(){
        List<HashMap<String, String>> result = new ArrayList<>();
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();

        for(LeaveRequest leaveRequest: leaveRequests){
                HashMap<String, String> element = new HashMap<>();
                element.put("id", leaveRequest.getId().toString());
                element.put("leaveRequestType", leaveRequest.getLeaveRequestType().getName());
                element.put("leaveRequestTypeId", leaveRequest.getLeaveRequestType().getId().toString());
                element.put("user", leaveRequest.getUser().getSurname() + " " + leaveRequest.getUser().getName());
                element.put("user_id", leaveRequest.getUser().getId().toString());
                element.put("startDate", leaveRequest.getStartDate().toString());
                element.put("endDate", leaveRequest.getEndDate().toString());
                element.put("period", String.valueOf(Period.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()).getDays() + 1));
                element.put("status", leaveRequest.getStatus().getName());
                element.put("requestDate", leaveRequest.getRequestDate().toString());
                result.add(element);
        }
        return result;
    }

}
