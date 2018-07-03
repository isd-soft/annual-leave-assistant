package isd.internship.ala.controllers;


import isd.internship.ala.models.LeaveRequest;
import isd.internship.ala.models.LeaveRequestType;
import isd.internship.ala.models.Status;
import isd.internship.ala.models.User;
import isd.internship.ala.repositories.LeaveRequestRepository;
import isd.internship.ala.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
import java.util.*;


@RestController
@RequestMapping(value = "/ala/leaveRequests")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private LeaveRequestTypeService leaveRequestTypeService;

    @Autowired
    private StatusService statusService;



//    @RequestMapping(value = "/all/{statusId}", method = RequestMethod.GET)
//    public List<LeaveRequest> getAllPending(@PathVariable(name = "statusId") Status statusId) {
//        return leaveRequestService.findAllPending(statusId);
//    }
//
//    @RequestMapping(value = "/all/{userId}/{statusId}", method = RequestMethod.GET)
//    public List<LeaveRequest> getAllPendingByUserId(@PathVariable(name = "userId") User userId, @PathVariable(name = "statusId") Status statusId) {
//        return leaveRequestService.findAllPendingByUserId(userId, statusId);
//    }
//
//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    public List<LeaveRequest> getAll() {
//        return leaveRequestService.getAll();
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public LeaveRequest getById(@PathVariable(name = "id") Integer id) {
//        return leaveRequestService.getById(id);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void deleteById(@PathVariable(name = "id") Integer id) {
//        leaveRequestService.deleteById(id);
//    }




    @PostMapping(produces = "application/json")
    public ResponseEntity<HashMap<String, String>>create (@RequestHeader(value = "Authorization") String header,
                                                          @RequestBody LeaveRequest leaveRequest) {
        HashMap<String, String> result = new HashMap<>();
        Status pending = statusService.getByName("pending");
            try {
                User foundUser = userService.findById(tokenService.getId(header));
                leaveRequest.setUser(foundUser);
                if(!leaveRequestService.alreadyRequested(leaveRequest) || foundUser.getAvailDays() !=  0) {
                    LeaveRequestType type = leaveRequestTypeService.getById(leaveRequest.getLeaveRequestType().getId());
                    leaveRequest.setLeaveRequestType(type);
                    leaveRequest.setStatus(pending);

//                    System.out.println("--------------------");
//                    System.out.println(leaveRequest.getLeaveRequestType().getName());
//                    System.out.println(leaveRequest.getStartDate());
//                    System.out.println(leaveRequest.getEndDate());
//                    System.out.println(leaveRequest.getRequestDate());
//                    System.out.println(leaveRequest.getStatus().getName());
//                    System.out.println(leaveRequest.getUser().getEmail());
//                    System.out.println("--------------------");

                    Period period = Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate());
                    System.out.println(period.getDays());


                    int year = Calendar.getInstance().get(Calendar.YEAR);

                    int availDays = foundUser.getAvailDays();
                    int takenDays = 28 - availDays;
                    boolean hasFourteenDays = leaveRequestService.taked14days(foundUser.getId(), year);
                    int requestedDays = Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getDays();


                    System.out.println("AvailableDays: " + availDays + " / Has14:" + hasFourteenDays + " / takenDays = " + takenDays + " / reqDays = " + requestedDays);

                    if (requestedDays > availDays) {
                        result.put("message", "You request too many days, man!");
                        return ResponseEntity.status(200).body(result);
                    }

                    if (requestedDays == 14)
                        hasFourteenDays = true;

                    if (!hasFourteenDays && (takenDays + requestedDays > 14)) {
                        result.put("message", "You should request 14 days!");
                        return ResponseEntity.status(200).body(result);
                    }

                    foundUser.setAvailDays(foundUser.getAvailDays() - requestedDays);
                    leaveRequestService.create(leaveRequest);
                    result.put("message", "LeaveRequest creation success!");
                    return ResponseEntity.status(201).body(result);
                } else {
                    result.put("message", "You've already requested these days or there are no available days left for you!");
                    return ResponseEntity.status(404).body(result);
                }

            } catch(NoSuchElementException e) {

                System.out.println("User not found!");
                result.put("message", "User not found!");
                return ResponseEntity.status(404).body(result);
            }
    }
}
