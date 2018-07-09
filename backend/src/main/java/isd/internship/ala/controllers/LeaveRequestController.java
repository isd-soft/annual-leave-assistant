package isd.internship.ala.controllers;


import isd.internship.ala.models.*;
import isd.internship.ala.repositories.*;
import isd.internship.ala.services.*;
import isd.internship.ala.utility.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
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

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoleRepository roleRepository;




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
        boolean isAdmin = tokenService.isAdmin(header);
            try {
                Long user_id = null;
                User foundUser = null;

                try {
                    user_id = leaveRequest.getUser().getId();
                    if(isAdmin)
                        foundUser = userRepository.findById(user_id).get();

                } catch (NullPointerException e){
                    foundUser = userRepository.findById(tokenService.getId(header)).get();
                    System.out.println("USER_ID NOT FOUND");
                }

                leaveRequest.setUser(foundUser);
                if(!leaveRequestService.alreadyRequested(leaveRequest) && (foundUser.getAvailDays() !=  0)) {
                    LeaveRequestType type = leaveRequestTypeRepository.findById(leaveRequest.getLeaveRequestType().getId()).get();
                    leaveRequest.setLeaveRequestType(type);
                    leaveRequest.setStatus(pending);

                    Period period = Period.between(leaveRequest.getStartDate(), leaveRequest.getEndDate());
                    System.out.println(period.getDays());

                    System.out.println("TYPE: " + type.getName() + " = " + type.getName().equals("Annual"));

                    if(type.getName().equals("Annual")) {
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
                    }

                    User user = leaveRequest.getUser();
                    Role role = roleRepository.findByRole("ADMIN");
                    List<User> admins = userRepository.findAllByRole(role);
                    System.out.println("this line");
                    System.out.println(admins.get(0));
                    emailService.sendAdminNotification(admins, user);

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
            } catch(MailException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body(result);
            }
    }


    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<HashMap<String, String>> updateLeaveRequest(@RequestHeader(value = "Authorization") String header,
                                                                      @RequestBody LeaveRequest leaveRequest,
                                                                      @PathVariable(name = "id") Integer id){
        HashMap<String, String> result = new HashMap<>();
        boolean isAdmin = tokenService.isAdmin(header);

        System.out.println("R: " + id);
        System.out.println("R: " + leaveRequest.getStartDate());
        System.out.println("R: " + leaveRequest.getEndDate());
        System.out.println("R: " + leaveRequest.getLeaveRequestType().getId());

        try{
            LeaveRequest foundLeaveRequest = leaveRequestRepository.findById(id).get();

            if(foundLeaveRequest.getStatus().getName().equals("approved" ) && !isAdmin){
                result.put("message", "You can't edit approved leaveRequest");
                return ResponseEntity.status(403).body(result);
            }

            if(tokenService.getId(header).equals(foundLeaveRequest.getUser().getId()) || isAdmin){
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

                System.out.println(leaveRequest.getStatus().getId());
                if(leaveRequest.getStatus().getId() != null && !leaveRequest.getStatus().getId().equals(foundLeaveRequest.getStatus().getId()) && isAdmin){
                    System.out.println("Changing status ...");
                    Status status = statusRepository.findById(leaveRequest.getStatus().getId()).get();
                    foundLeaveRequest.setStatus(status);
                    emailService.sendUserNotification(leaveRequest.getUser());
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
            return ResponseEntity.status(404).body(result);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> deleteLeaveRequest(@RequestHeader(value = "Authorization") String header,
                                                                      @PathVariable(name = "id") Integer id){
        HashMap<String, String> result = new HashMap<>();
        boolean isAdmin = tokenService.isAdmin(header);

        try{
            LeaveRequest foundLeaveRequest = leaveRequestRepository.findById(id).get();
            if((tokenService.getId(header).equals(foundLeaveRequest.getUser().getId()) && (foundLeaveRequest.getStatus().getName().equals("pending"))) || isAdmin){
                leaveRequestRepository.delete(foundLeaveRequest);
                result.put("message", "Delete success!");
                return ResponseEntity.status(200).body(result);
            } else {
                System.out.println("[ ! ]   Attempt to change other user's data!");
                result.put("message", "Forbidden: you have no rights!");
                return ResponseEntity.status(403).body(result);
            }
        } catch (Exception e){
            System.out.println("[ ! ]   Attempt to change other user's data!");
            result.put("message", "NoSuchElementException!");
            return ResponseEntity.status(404).body(result);
        }
    }
}
