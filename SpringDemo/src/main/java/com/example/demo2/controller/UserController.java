package com.example.demo2.controller;

import com.example.demo2.model.Role;
import com.example.demo2.Service.RoleDAOImpl;
import com.example.demo2.utility.EmailService;
import com.example.demo2.model.User;
import com.example.demo2.Service.UserDAOImpl;
import com.example.demo2.utility.PDFLeaveRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;



import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin("*")
@RestController
public class UserController {

    @Autowired
    UserDAOImpl userDAO;

    @Autowired
    RoleDAOImpl roleDAO;

    @Autowired
    EmailService emailService;


    @PostMapping("/login")
    public Map<String, String> response(@RequestBody Map<String, String>map){
        String email = map.get("email");
        String password = map.get("password");

        Map<String, String> newMap = new HashMap<>();

        User user = userDAO.findByEmail(email);
        if(user == null) {
            newMap.put("id","Such user was not found!");
            return newMap;
        }
        if(user.getPassword().equals(password)) {
            String id = String.valueOf(user.getId());
            newMap.put("id", id);
            return newMap;
        } else {
            newMap.put("id", "Wrong password!");
            return newMap;
        }
    }

    @PostMapping("/registration")
    public Map<String, String> registrate(@RequestBody Map<String, String>map){
        String email = map.get("email");
        Map<String, String> newMap = new HashMap<>();

        User user = userDAO.findByEmail(email);
        if(user == null) {
            newMap.put("status", "OK");
        } else {
            newMap.put("status", "FOUND");
        }
        return newMap;
    }

    @PostMapping("/completeRegistration")
    public Map<String, String> createUser(@RequestBody User user) {
        Map<String, String> newMap = new HashMap<>();
        userDAO.saveUser(user);
        System.out.println(user.getRole().getRole());
        User user2 = userDAO.findByEmail(user.getEmail());
        String id = String.valueOf(user2.getId());
        newMap.put("id", id);
        return newMap;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        System.out.println(id);
        User user = userDAO.findUserbyId(id);

        return user;
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") Long id,
                           @RequestBody User user) {
        User user2 = userDAO.findUserbyId(id);
        Role role =  roleDAO.findRole(user.getRole().getRole());
        user2 = user;
        user2.setRole(role);

        User updatedUser = userDAO.saveUser(user2);

        return updatedUser;
    }

    @GetMapping("/verifyEmail/{email}")
    public Map<String, String> verifyEmail(@PathVariable(value = "email") String email){
        User user = userDAO.findByEmail(email);
        Map<String, String> newMap = new HashMap<>();
        if(user == null) {
            newMap.put("status", "OK");
            return newMap;
        } else {
            newMap.put("status", "FOUND");
            return newMap;
        }
    }

    @GetMapping("/generate-pdf")
    public ResponseEntity<InputStreamResource> generateLeaveRequests(
            @RequestParam(value = "email") String email) {

        User user = userDAO.findByEmail(email);
        ByteArrayInputStream bis = PDFLeaveRequest.leaveRequestAnual(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",  "filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));


    }

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam(value = "email") String email) {
        User user = userDAO.findByEmail(email);

        try {
            emailService.sendEmail(user);
        } catch(MailException e) {
            e.printStackTrace();
            return "Email not sent";
        } catch(NullPointerException e) {
            e.printStackTrace();
            return "There is no such email in database!";
        }

        return "Email was send succesfully!";
    }
}


