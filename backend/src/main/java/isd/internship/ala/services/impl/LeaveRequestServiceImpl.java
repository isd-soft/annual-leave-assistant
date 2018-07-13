package isd.internship.ala.services.impl;

import isd.internship.ala.models.*;
import isd.internship.ala.repositories.HolidayRepository;
import isd.internship.ala.repositories.LeaveRequestRepository;
import isd.internship.ala.repositories.StatusRepository;
import isd.internship.ala.services.LeaveRequestMaximumDays;
import isd.internship.ala.services.LeaveRequestService;
import isd.internship.ala.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HolidayRepository holidayRepository;

    @Override
    public LeaveRequest create(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }


    @Override
    public int getTotalDays(User user, Integer year, String type){
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();
        int totalDays = 0;
        for (LeaveRequest leaveRequest : leaveRequests) {
            if (leaveRequest.getUser().equals(user) &&
                    leaveRequest.getLeaveRequestType().getName().equals(type) &&
                    (leaveRequest.getStartDate().getYear() == leaveRequest.getEndDate().getYear()) &&
                    (leaveRequest.getStartDate().getYear() == year))
                if(Period.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()).getMonths() == 0)
                    totalDays = Period.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()).getDays() + 1;
                else
                    totalDays = Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getMonths() * 30 + Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getDays() + 2;

        }
        return totalDays;
    }

    @Override
    public boolean taked14days(Long user_id, Integer year){
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();
        for (LeaveRequest leaveRequest : leaveRequests) {
            if (leaveRequest.getUser().getId().equals(user_id) &&
                    (leaveRequest.getStartDate().getYear() == leaveRequest.getEndDate().getYear()) &&
                    (leaveRequest.getStartDate().getYear() == year) &&
                    (Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getDays() + 1 >= 14))
                return true;
        }
        return false;
    }

    @Override
    public boolean alreadyRequested(LeaveRequest leaveRequest, User user){
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();
        LocalDate startDate = leaveRequest.getStartDate();
        LocalDate endDate = leaveRequest.getEndDate();
        for(LeaveRequest lr: leaveRequests) {
            if(lr.getUser().equals(user) && ( (lr.getStartDate().equals(startDate) ||
                                                                lr.getEndDate().equals(endDate)) ||
                                                                lr.getStartDate().equals(endDate) ||
                                                                lr.getEndDate().equals(startDate) ||
                    (startDate.isBefore(lr.getEndDate()) && startDate.isAfter(lr.getStartDate())) ||
                    (endDate.isBefore(lr.getEndDate()) && endDate.isAfter(lr.getStartDate()))) ){
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
            String period = null;
            if(Period.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()).getMonths() == 0)
                period = String.valueOf(Period.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()).getDays() + 1);
            else
                period = String.valueOf(Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getMonths() * 30 + Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getDays() + 2);

            if(leaveRequest.getUser().getId().equals(id)){
                HashMap<String, String> element = new HashMap<>();
                element.put("id", leaveRequest.getId().toString());
                element.put("leaveRequestType", leaveRequest.getLeaveRequestType().getName());
                element.put("leaveRequestTypeId", leaveRequest.getLeaveRequestType().getId().toString());
                element.put("user", leaveRequest.getUser().getSurname() + " " + leaveRequest.getUser().getName());
                element.put("startDate", leaveRequest.getStartDate().toString());
                element.put("endDate", leaveRequest.getEndDate().toString());
                element.put("period", period);
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
                String period = null;
                if(Period.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()).getMonths() == 0)
                    period = String.valueOf(Period.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()).getDays() + 1);
                else
                    period = String.valueOf(Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getMonths() * 30 + Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getDays() + 2);

            HashMap<String, String> element = new HashMap<>();
                element.put("id", leaveRequest.getId().toString());
                element.put("leaveRequestType", leaveRequest.getLeaveRequestType().getName());
                element.put("leaveRequestTypeId", leaveRequest.getLeaveRequestType().getId().toString());
                element.put("user", leaveRequest.getUser().getSurname() + " " + leaveRequest.getUser().getName());
                element.put("user_id", leaveRequest.getUser().getId().toString());
                element.put("startDate", leaveRequest.getStartDate().toString());
                element.put("endDate", leaveRequest.getEndDate().toString());
                element.put("period", period);
                element.put("status", leaveRequest.getStatus().getName());
                element.put("requestDate", leaveRequest.getRequestDate().toString());
                result.add(element);
        }
        return result;
    }


    @Override
    public String check(LeaveRequest leaveRequest,LeaveRequestType type, User foundUser) {
        Period period = Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate());
        System.out.println(period.getDays());

        System.out.println("TYPE: " + type.getName() + " = " + type.getName().equals("Annual"));

        if (type.getName().equals("Annual")) {
            int year = Calendar.getInstance().get(Calendar.YEAR);

            System.out.println("LEAVEREQUEST USER: " + foundUser.getName());
            int availDays = foundUser.getAvailDays();
            boolean hasFourteenDays = taked14days(foundUser.getId(), year);
            int requestedDays = Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getDays() + 1;

            System.out.println("AvailableDays: " + foundUser.getAvailDays() + " / Has14:" + hasFourteenDays + " / reqDays = " + requestedDays);

            if (requestedDays > availDays) {
                return "You request too many days, man!";
            }

            if ((requestedDays >= 14) || (requestedDays == foundUser.getAvailDays()))
                hasFourteenDays = true;

            if (!hasFourteenDays && (availDays - requestedDays < 14)) {
                return "You should request 14 days!";
            }
        }



        if (type.getName().equals("Personal")) {
            int year = Calendar.getInstance().get(Calendar.YEAR);

            System.out.println("LEAVEREQUEST USER: " + foundUser.getName());
            int days = getTotalDays(foundUser, year, "Personal");

            int requestedDays;

            if(Period.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()).getMonths() == 0)
                requestedDays = Period.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()).getDays() + 1;
            else
                requestedDays = Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getMonths() * 30 + Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getDays() + 2;

            if (days == 90)
                return "You reached the max number of days for Personal leave requests!";

            if(requestedDays + days > 90)
                return "You request too many days!";
        }



        if (type.getName().equals("Marriage")) {
            int year = Calendar.getInstance().get(Calendar.YEAR);

            System.out.println("LEAVEREQUEST USER: " + foundUser.getName());
            int days = getTotalDays(foundUser, year, "Marriage");
            int requestedDays;

            if(Period.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()).getMonths() == 0)
                requestedDays = Period.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()).getDays() + 1;
            else
                requestedDays = Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getMonths() * 30 + Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getDays() + 2;


            if (days == 3)
                return "You reached the max number of days for Marriage leave requests!";

            if(requestedDays + days > 3)
                return "You request too many days!";
        }



        if (type.getName().equals("Paternity")) {
            int year = Calendar.getInstance().get(Calendar.YEAR);

            System.out.println("LEAVEREQUEST USER: " + foundUser.getName());
            int days = getTotalDays(foundUser, year, "Paternity");
            int requestedDays;

            if(Period.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()).getMonths() == 0)
                requestedDays = Period.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()).getDays() + 1;
            else
                requestedDays = Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getMonths() * 30 + Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getDays() + 2;


            if (days == 14)
                return "You reached the max number of days for Paternal leave requests!";

            if(requestedDays + days > 14)
                return "You request too many days!";
        }

        return "Accepted";
    }

    @Override
    public void checkForHoliday(LeaveRequest leaveRequest){
        List<Holiday> holidays = holidayRepository.findAll();
        int result = 0;
        for(Holiday holiday : holidays)
            if((holiday.getDate().isAfter(leaveRequest.getStartDate()) && holiday.getDate().isBefore(leaveRequest.getEndDate())) ||
                    holiday.getDate().equals(leaveRequest.getStartDate()) || holiday.getDate().equals(leaveRequest.getEndDate()) )
                result++;

        User user = leaveRequest.getUser();
        user.setAvailDays(user.getAvailDays() + result);
    }

}
