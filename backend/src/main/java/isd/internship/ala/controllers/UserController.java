package isd.internship.ala.controllers;

import java.util.*;

import javax.servlet.ServletException;

import isd.internship.ala.models.User;
import isd.internship.ala.security.JwtGenerator;
import isd.internship.ala.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    private JwtGenerator jwtGenerator;

    public UserController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @GetMapping("/ala/hello")
    public String hello() {
        return "Buna ziua, hello, здраствуй, bonjour . . .";
    }

    // LogIn and get a token [checked]
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<HashMap<String, String>> generate(@RequestBody final User user) {
        return ResponseEntity.status(200).body((jwtGenerator.generate(user)));
    }

    // Register a new user [checked]
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<HashMap<String, String>> registerUser(@RequestBody User user) {
        HashMap<String, String> result = new HashMap<>();
        try{
            userService.findByEmail(user.getEmail()).get();
            System.out.println("[ ! ]   User with this email already exists!");
            result.put("message","User with this email already exists!");
            return ResponseEntity.status(409).body(result);
        } catch (NoSuchElementException e){
            System.out.println("[ R ]   User " + user.getEmail() + " registered successfully!");
            userService.save(user);
            result.put("message","Registration success.");
        }
            return ResponseEntity.status(201).body(result);
    }

    @PostMapping(value = "/users/{id}/update", produces = "application/json")
    public ResponseEntity<HashMap<String, String>> updateUser(@RequestBody User user) {
        HashMap<String, String> result = new HashMap<>();
        try{
            userService.findByEmail(user.getEmail()).get();
            System.out.println("[ U ]   User found. Data updated.");
            result.put("message","Data updated.");
            userService.save(user);
            return ResponseEntity.status(200).body(result);
        } catch (NoSuchElementException e){
            System.out.println("[ ! ]   User " + user.getEmail() + " not found!");
            result.put("message","User not found.");
        }
        return ResponseEntity.status(201).body(result);
    }



    @RequestMapping(value = "/ala/users", method = RequestMethod.GET)
    public List<User> getAll(){
        return userService.findAll();
    }
}
