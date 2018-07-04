package isd.internship.ala.controllers;


import isd.internship.ala.models.LeaveRequest;
import isd.internship.ala.models.LeaveRequestType;
import isd.internship.ala.models.Status;
import isd.internship.ala.models.User;
import isd.internship.ala.repositories.LeaveRequestRepository;
import isd.internship.ala.repositories.LeaveRequestTypeRepository;
import isd.internship.ala.repositories.StatusRepository;
import isd.internship.ala.repositories.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private LeaveRequestTypeService leaveRequestTypeService;

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private LeaveRequestTypeRepository leaveRequestTypeRepository;

    @Autowired
    private StatusService statusService;

    @Autowired
    private StatusRepository statusRepository;




    @GetMapping(produces = "application/json")
    public ResponseEntity<List<HashMap<String, String>>> getLeaveRequests(@RequestHeader(value = "Authorization") String header){
        Long id = tokenService.getId(header);
        if(tokenService.isAdmin(header))
            return ResponseEntity.status(200).body(leaveRequestService.getAll());
        else
            return ResponseEntity.status(200).body(leaveRequestService.getByUserId(id));
    }


    @PostMapping(produces = "application/json")
    public ResponseEntity<HashMap<String, String>>create (@RequestHeader(value = "Authorization") String header,
                                                          @RequestBody LeaveRequest leaveRequest) {
        HashMap<String, String> result = new HashMap<>();
        Status pending = statusService.getByName("pending");
            try {
                User foundUser = userRepository.findById(tokenService.getId(header)).get();
                leaveRequest.setUser(foundUser);
                if(!leaveRequestService.alreadyRequested(leaveRequest) && (foundUser.getAvailDays() !=  0)) {
                    LeaveRequestType type = leaveRequestTypeRepository.findById(leaveRequest.getLeaveRequestType().getId()).get();
                    leaveRequest.setLeaveRequestType(type);
                    leaveRequest.setStatus(pending);

                    Period period = Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate());
                    System.out.println(period.getDays());


                    int year = Calendar.getInstance().get(Calendar.YEAR);

                    int availDays = foundUser.getAvailDays();
                    boolean hasFourteenDays = leaveRequestService.taked14days(foundUser.getId(), year);
                    int requestedDays = Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()).getDays() + 1;


                    System.out.println("AvailableDays: " + availDays + " / Has14:" + hasFourteenDays + " / reqDays = " + requestedDays);

                    if (requestedDays > availDays) {
                        result.put("message", "You request too many days, man!");
                        return ResponseEntity.status(200).body(result);
                    }

                    if (requestedDays == 14)
                        hasFourteenDays = true;

                    if (!hasFourteenDays && (availDays - requestedDays < 14)) {
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

                System.out.println("NoSuchElement exception !");
                result.put("message", "User not found!");
                return ResponseEntity.status(404).body(result);
            }
    }


    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<HashMap<String, String>> updateLeaveRequest(@RequestHeader(value = "Authorization") String header,
                                                      @RequestBody LeaveRequest leaveRequest,
                                                      @PathVariable(name = "id") Integer id){
        HashMap<String, String> result = new HashMap<>();
        boolean isAdmin = tokenService.isAdmin(header);

        try{
            User foundUser = userRepository.findById(tokenService.getId(header)).get();
            System.out.println("USER FOUND");
            LeaveRequest foundLeaveRequest = leaveRequestRepository.findById(id).get();
            System.out.println("REQUEST FOUND");

            if(foundLeaveRequest.getStatus().getName().equals("approved" ) && !isAdmin){
                result.put("message", "You can't edit approved leaveRequest");
                return ResponseEntity.status(403).body(result);
            }

            if(tokenService.getId(header).equals(foundUser.getId()) || isAdmin){
                if(leaveRequest.getLeaveRequestType().getId() != null && !leaveRequest.getLeaveRequestType().getId().equals(foundLeaveRequest.getLeaveRequestType().getId())){
                    LeaveRequestType type = leaveRequestTypeRepository.findById(leaveRequest.getLeaveRequestType().getId()).get();
                    foundLeaveRequest.setLeaveRequestType(type);
                    System.out.println("LeaveRequestType changed");
                }

                if(leaveRequest.getStartDate() != null && !leaveRequest.getStartDate().equals(foundLeaveRequest.getStartDate())){
                    foundLeaveRequest.setStartDate(leaveRequest.getStartDate());
                    System.out.println("StartDate changed");
                }

                if(leaveRequest.getEndDate() != null && !leaveRequest.getEndDate().equals(foundLeaveRequest.getEndDate())){
                    foundLeaveRequest.setEndDate(leaveRequest.getEndDate());
                    System.out.println("EndDate changed");
                }


                if(leaveRequest.getStatus().getId() != null && !leaveRequest.getStatus().getId().equals(foundLeaveRequest.getStatus().getId()) && isAdmin){
                    Status status = statusRepository.findById(leaveRequest.getStatus().getId()).get();
                    foundLeaveRequest.setStatus(status);
                    System.out.println("Status changed");
                }

                leaveRequestService.create(foundLeaveRequest);
                result.put("message", "LeaveRequest Data updated");
                return ResponseEntity.status(200).body(result);
            } else {
                System.out.println("[ ! ]   Attempt to change other user's data!");
                result.put("message", "Forbidden: you have no rights!");
                return ResponseEntity.status(403).body(result);
            }
        } catch (Exception e){
            result.put("message", "NoSuchElement exception");
        }
        return ResponseEntity.status(404).body(result);
    }
}
