package isd.internship.ala.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import isd.internship.ala.models.User;
import isd.internship.ala.repositories.UserRepository;
import isd.internship.ala.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class JwtGenerator {
    @Autowired
    private UserService userService;

    public HashMap<String, String> generate(User user) {
        HashMap<String, String> result = new HashMap<>();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        try {
            User usr = userService.findByEmail(user.getEmail()).get();
            System.out.println(user.getPassword() + "   <=>    " + user.getEmail());
            System.out.println(usr.getPassword() + "   <=>    " + usr.getEmail());
            System.out.println("RESULT:  " + encoder.matches(user.getPassword(), usr.getPassword()));

            if (encoder.matches(user.getPassword(), usr.getPassword())) {
                System.out.println("User Found. Generating token . . .");

                Claims claims = Jwts.claims().setSubject(user.getEmail());
//                claims.put("password", user.getPassword());
//                claims.put("role", user.getRole());

                String token = Jwts.builder()
                        .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS512, "inther")
                        .compact();


                result.put("token",token);

                return result;
            } else {
                result.put("message", "Invalid credentials!");
                System.out.println("Incorrect Password");
            }
        } catch(NoSuchElementException e) {
            result.put("message", "Invalid credentials!");
            System.out.println("Incorrect Email !");
            }
        return result;
    }

}
