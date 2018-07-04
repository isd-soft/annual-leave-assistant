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
        for(LeaveRequest lr: leaveRequests) {
            if(lr.getUser().equals(leaveRequest.getUser()) && ( lr.getStartDate().equals(leaveRequest.getStartDate()) ||
                                                                lr.getEndDate().equals(leaveRequest.getEndDate()) )){
                return true;
            }
        }
        return false;
    }




    @Override
    public List<HashMap<String, String>> getByUserId(Long id){
        List<HashMap<String, String>> result = new ArrayList<>();
        HashMap<String, String> element = new HashMap<>();
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();

        for(LeaveRequest leaveRequest: leaveRequests){
            if(leaveRequest.getUser().getId().equals(id)){
                element.put("leaveRequestType", leaveRequest.getLeaveRequestType().getName());
                element.put("user", leaveRequest.getUser().getId().toString());
                element.put("startDate", leaveRequest.getStartDate().toString());
                element.put("endDate", leaveRequest.getEndDate().toString());
                element.put("status", leaveRequest.getStatus().getName());
                element.put("requestDate", leaveRequest.getRequestDate().toString());
                result.add(element);
                System.out.println(element);
            }
        }
        return result;
    }


//    @Override
//    public List<LeaveRequest> getAll() {
//        return leaveRequestRepository.findAll();
//    }
//
//    @Override
//    public List<LeaveRequest> findAllByUserId(User id) {
//        return leaveRequestRepository.findAllByUserIdEqualsOrderByRequestDate(id);
//    }
//
//    @Override
//    public List<LeaveRequest> findAllPending(Status statusId) {
//        return leaveRequestRepository.findAllByStatusIdEqualsOrderByRequestDate(statusId);
//    }
//
//    @Override
//    public List<LeaveRequest> findAllPendingByUserId(User userId, Status statusId) {
//        return leaveRequestRepository.findAllByUserIdEqualsAndStatusIdEquals(userId, statusRepository.getOne(1));
//    }
//
//    @Override
//    public LeaveRequest getById(Integer id) { return leaveRequestRepository.getOne(id); }
//
//    @Override
//    public LeaveRequest create(LeaveRequest leaveRequest) {
//
//        if (CheckAvailableDays(leaveRequest)){
//            return leaveRequestRepository.save(leaveRequest);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public LeaveRequest update(LeaveRequest leaveRequest, Integer id) {
//        LeaveRequest leaveRequestNew = leaveRequestRepository.getOne(id);
//        leaveRequestNew.setLeaveRequestType(leaveRequest.getLeaveRequestType());
//        leaveRequestNew.setUser(leaveRequest.getUser());
//        leaveRequestNew.setRequestDate(leaveRequest.getRequestDate());
//        leaveRequestNew.setStartDate(leaveRequest.getStartDate());
//        leaveRequestNew.setEndDate(leaveRequest.getEndDate());
//        leaveRequestNew.setStatus(leaveRequest.getStatus());
//        leaveRequestRepository.save(leaveRequestNew);
//        return leaveRequestNew;
//    }
//
//    @Override
//    public void deleteById(Integer id) {
//        leaveRequestRepository.deleteById(id);
//    }
//
//    @Override
//    public void deleteAll() {
//        leaveRequestRepository.deleteAll();
//    }
//
//    public Integer DateDiff (Date startDate, Date endDate) {
//        LocalDate dateFrom = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate dateTo = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//        Period intervalPeriod = Period.between(dateFrom, dateTo);
//        if(intervalPeriod.getMonths()==0 && intervalPeriod.getYears()==0) {
//            return intervalPeriod.getDays()+1;
//        }else {
//            return 0;
//        }
//    }
//
//    public boolean CheckAvailableDays (LeaveRequest leaveRequest) {
//        List<LeaveRequest> totalLeaveRequests = leaveRequestRepository.findAllByUserIdEqualsAndStatusIdEquals(leaveRequest.getUser(),statusRepository.getOne(2));
//        Integer totalDaysOff = 0;
//        LocalDate dateNow = LocalDate.now();
//        LocalDate date = LocalDate.of(dateNow.getYear()-1, 12, 31);
//        for (LeaveRequest item :totalLeaveRequests) {
//            if(item.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(date)) {
//                totalDaysOff += DateDiff(item.getStartDate(), item.getEndDate());
//            }
//        }
//        Integer currentDaysOff = DateDiff(leaveRequest.getStartDate(), leaveRequest.getEndDate());
//        if (totalDaysOff<MaximumDaysAvailable && MaximumDaysAvailable - totalDaysOff >=currentDaysOff) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//    public boolean CheckFourTeenDays (LeaveRequest leaveRequest) {
//        List<LeaveRequest> totalLeaveRequests = leaveRequestRepository.findAllByUserIdEqualsAndStatusIdEquals(leaveRequest.getUser(), statusRepository.getOne(2));
//        Integer totalDaysOff = 0;
//        LocalDate dateNow = LocalDate.now();
//        LocalDate date = LocalDate.of(dateNow.getYear()-1, 12, 31);
//
//        List <Integer> leaves = new ArrayList<>();
//
//        for (LeaveRequest item :totalLeaveRequests) {
//            if(item.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(date)) {
//                leaves.add(DateDiff(item.getStartDate(),item.getEndDate())+1);
//            }
//        }
//
//        boolean moreThan14 = Fourteen <= leaves.stream().filter(v-> v>14).findAny().get();
//        if (moreThan14){
//            return true;
//        }else{
//            return false;
//        }
//    }


}