package isd.internship.ala.controllers;

import java.util.*;

import javax.servlet.ServletException;

import io.jsonwebtoken.Claims;
import isd.internship.ala.models.JwtAuthenticationToken;
import isd.internship.ala.models.Role;
import isd.internship.ala.models.User;
import isd.internship.ala.repositories.RoleRepository;
import isd.internship.ala.security.JwtGenerator;
import isd.internship.ala.services.TokenService;
import isd.internship.ala.services.UserService;
import isd.internship.ala.services.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenService tokenService;

    private JwtGenerator jwtGenerator;

    public UserController(JwtGenerator jwtGenerator, TokenServiceImpl tokenServiceImpl) {
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
        try {
            userService.findByEmail(user.getEmail()).get();
            System.out.println("[ ! ]   User with this email already exists!");
            result.put("message","User with this email already exists!");
            return ResponseEntity.status(409).body(result);
        } catch (NoSuchElementException e) {
            System.out.println("[ R ]   User " + user.getEmail() + " registered successfully!");
            user.setRole(roleRepository.findByRole("USER")); // Add new user to group USER
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userService.save(user);
            result.put("message","Registration success");
        }
        return ResponseEntity.status(201).body(result);
    }

    // UPDATE USER INFO [checked]
    @PutMapping(value = "/ala/users/{id}", produces = "application/json")
    public ResponseEntity<HashMap<String, String>> updateUser(@RequestHeader(value = "Authorization") String header,
                                                              @RequestBody User user,
                                                              @PathVariable(name = "id") Long id) {
        HashMap<String, String> result = new HashMap<>();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean isAdmin = tokenService.isAdmin(header);

        try{
            User foundUser = userService.findById(id);
            if(tokenService.getId(header) == foundUser.getId() || isAdmin){
                if(user.getSurname() != null)
                    foundUser.setSurname(user.getSurname());

                if(user.getName() != null)
                    foundUser.setName(user.getName());

                if(user.getEmail() != null)
                    foundUser.setEmail(user.getEmail());

                if(user.getPassword() != null)
                    foundUser.setPassword(encoder.encode(user.getPassword()));

                if(user.getEmpDate()  != null)
                    foundUser.setEmpDate(user.getEmpDate());

                if(user.getRole() != null && isAdmin)
                    foundUser.setRole(user.getRole());

                userService.save(foundUser);
                System.out.println("[ U ]   Data for " + foundUser.getEmail() + " updated.");
                result.put("message","Data updated");
                return ResponseEntity.status(200).body(result);
            } else {
                System.out.println("[ ! ]   Attempt to change other user's data!");
                result.put("message","Forbidden: you have no rights!");
                return ResponseEntity.status(403).body(result);
            }

        } catch (NoSuchElementException e){
            System.out.println("[ ! ]   User " + user.getId() + " not found!");
            result.put("message","User not found");
        }
        return ResponseEntity.status(201).body(result);
    }


    // GET user list
    @GetMapping(value = "/ala/users", produces = "application/json")
    public ResponseEntity<List<User>> getAll(@RequestHeader(value = "Authorization") String header) {
        if(tokenService.isAdmin(header))
            return ResponseEntity.status(200).body(userService.findAll());
        else
            return ResponseEntity.status(403).body(null);
    }


    @DeleteMapping(value = "/ala/users/{id}", produces = "application/json")
    public ResponseEntity<HashMap<String, String>> deleteUser(Long id){
        return null;
    }
}
