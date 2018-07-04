package isd.internship.ala.controllers;

import java.util.*;

import isd.internship.ala.models.User;
import isd.internship.ala.repositories.RoleRepository;
import isd.internship.ala.repositories.UserRepository;
import isd.internship.ala.security.JwtGenerator;
import isd.internship.ala.services.LeaveRequestService;
import isd.internship.ala.services.TokenService;
import isd.internship.ala.services.UserService;
import isd.internship.ala.services.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LeaveRequestService leaveRequestService;


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
            result.put("message", "User with this email already exists!");
            return ResponseEntity.status(409).body(result);
        } catch (NoSuchElementException e) {
            System.out.println("[ R ]   User " + user.getEmail() + " registered successfully!");
            user.setRole(roleRepository.findByRole("USER")); // Add new user to group USER
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userService.save(user);
            result.put("message", "Registration success");
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
            User foundUser = userRepository.findById(id).get();
            if(tokenService.getId(header).equals(foundUser.getId()) || isAdmin){
                if(user.getSurname() != null && !user.getSurname().equals(foundUser.getSurname())) {
                    foundUser.setSurname(user.getSurname());
                    System.out.println("Surname changed");
                }

                if (user.getName() != null && !user.getName().equals(foundUser.getName())) {
                    foundUser.setName(user.getName());
                    System.out.println("Name changed");
                }

                if (user.getEmail() != null && !user.getEmail().equals(foundUser.getEmail())) {
                    foundUser.setEmail(user.getEmail());
                    System.out.println("Email changed");
                }


                if (user.getPassword() != null) {
                    foundUser.setPassword(encoder.encode(user.getPassword()));
                    System.out.println("Password changed");
                }


                if (user.getEmpDate() != null && !user.getEmpDate().equals(foundUser.getEmpDate())) {
                    foundUser.setEmpDate(user.getEmpDate());
                    System.out.println("EmpDate changed");
                }


                if (user.getRole() != null && isAdmin) {
                    foundUser.setRole(user.getRole());
                    System.out.println("Role changed");
                }


                if(user.getDepartment() != null){
                    foundUser.setDepartment(user.getDepartment());
                    System.out.println("Department changed");
                }


                if(user.getFunction() != null){
                    foundUser.setFunction(user.getFunction());
                    System.out.println("Function changed");
                }

                if(user.getAvailDays() != null){
                    foundUser.setAvailDays(user.getAvailDays());
                    System.out.println("AvailDays changed");
                }

                userService.save(foundUser);
                System.out.println("[ U ]   Data for " + foundUser.getEmail() + " updated.");
                result.put("message", "Data updated");
                return ResponseEntity.status(200).body(result);
            } else {
                System.out.println("[ ! ]   Attempt to change other user's data!");
                result.put("message", "Forbidden: you have no rights!");
                return ResponseEntity.status(403).body(result);
            }

        } catch (NoSuchElementException e) {
            System.out.println("[ ! ]   User " + user.getId() + " not found!");
            result.put("message", "User not found");
        }
        return ResponseEntity.status(404).body(result);
    }


    @GetMapping(value = "/ala/users/{id}", produces = "application/json")
    public ResponseEntity<HashMap<String, String>> findUser(@RequestHeader(value = "Authorization") String header,
                                         @PathVariable(name = "id") Long id) {

        boolean isAdmin = tokenService.isAdmin(header);
        HashMap<String, String> response = new HashMap<>();
        if (tokenService.getId(header) == id || isAdmin) {
            try {
                User foundUser = userRepository.findById(id).get();
                response.put("id", foundUser.getId().toString());
                response.put("surname", foundUser.getSurname());
                response.put("name", foundUser.getName());
                response.put("email", foundUser.getSurname());
                response.put("empDate", foundUser.getEmpDate().toString());
                response.put("role", foundUser.getRole().getRole());
                response.put("availDays", foundUser.getAvailDays().toString());
                response.put("function", foundUser.getFunction());
                response.put("department", foundUser.getDepartment());
                return ResponseEntity.status(200).body(response);
            } catch (NoSuchElementException e) {
                response.put("message", "User not found!");
                return ResponseEntity.status(404).body(response);
            }
        } else {
            response.put("message", "You have no power.");
            return ResponseEntity.status(403).body(response);
        }
    }


    // GET user list
    @GetMapping(value = "/ala/users", produces = "application/json")
    public ResponseEntity<List<User>> getAll(@RequestHeader(value = "Authorization") String header) {
        if (tokenService.isAdmin(header))
            return ResponseEntity.status(200).body(userService.findAll());
        else
            return ResponseEntity.status(403).body(null);
    }

    @DeleteMapping("ala/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id, @RequestHeader(value = "Authorization") String header) {
        System.out.println("Delete User with ID = " + id + "...");

        if (tokenService.isAdmin(header)) {
            userService.deleteUser(id);
            return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }

    @DeleteMapping("/ala/users/delete")
    public ResponseEntity<String> deleteAllUsers() {
        System.out.println("Delete All Users...");

        userService.deleteAllUsers();

        return new ResponseEntity<>("All users have been deleted!", HttpStatus.OK);
    }

    @PostMapping("/ala/users/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.save(user);
        return new ResponseEntity<>("User has been created", HttpStatus.OK);
    }
}
