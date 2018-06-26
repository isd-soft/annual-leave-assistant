package isd.internship.ala.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import isd.internship.ala.models.User;
import isd.internship.ala.repositories.UserRepository;
import isd.internship.ala.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {
            User usr = userService.findByEmail(user.getEmail()).get();

            if (usr.getPassword().equals(user.getPassword())) {
                System.out.println("User Found. Generating token . . .");

                Claims claims = Jwts.claims()
                        .setSubject(user.getEmail());
                claims.put("password", user.getPassword());
                claims.put("role", user.getRole());

                String token = Jwts.builder()
                        .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS512, "inther")
                        .compact();


                result.put("token",token);

                return result;
            }
        } catch(NoSuchElementException e) {
            result.put("message", "Invalid credentials!");
            System.out.println("User Not Found ! ! !");
            }
        return result;
    }

}
