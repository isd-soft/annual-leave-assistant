package isd.internship.ala.controllers;


import isd.internship.ala.models.LeaveRequest;
import isd.internship.ala.models.LeaveRequestType;
import isd.internship.ala.models.Status;
import isd.internship.ala.models.User;
import isd.internship.ala.repositories.LeaveRequestRepository;
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
    private StatusService statusService;



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
                    LeaveRequestType type = leaveRequestTypeService.getById(leaveRequest.getLeaveRequestType().getId());
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

                System.out.println("User not found!");
                result.put("message", "User not found!");
                return ResponseEntity.status(404).body(result);
            }
    }


    @PutMapping(value = "/{id}", produces = "application/json")
    public HashMap<String, String> updateLeaveRequest(@RequestHeader(value = "Authorization") String header,
                                                      @RequestBody LeaveRequest leaveRequest,
                                                      @PathVariable(name = "id") Long id){
        return null;

    }
}
