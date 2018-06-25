package isd.internship.ala.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import isd.internship.ala.models.User;
import isd.internship.ala.security.JwtGenerator;
import isd.internship.ala.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
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
    @PostMapping("/login")
    public String generate(@RequestBody final User user) {
        return jwtGenerator.generate(user);
    }

    // Register a new user [checked]
    @PostMapping(value = "/register")
    public String registerUser(@RequestBody User user) {
        List<User> users = userService.findAll();
        for(User u : users){
            if(u.getEmail().equals(user.getEmail()))
                return "User with this email exists";
        }
        userService.save(user);
        return "Registration success.";
    }

    @RequestMapping(value = "/ala/users", method = RequestMethod.GET)
    public List<User> getAll(){
        return userService.findAll();
    }
}
