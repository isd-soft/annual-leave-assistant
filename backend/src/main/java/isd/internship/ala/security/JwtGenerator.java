package isd.internship.ala.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import isd.internship.ala.models.User;
import isd.internship.ala.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtGenerator {
    @Autowired
    private UserRepository userRepository;

    public String generate(User user) {
        List<User> users = userRepository.findAll();

        for(User u : users){
            System.out.println(u.getEmail() + " <--> " + user.getEmail());
            System.out.println(u.getPassword() + " <--> " + user.getPassword());
            System.out.println(u.getRole() + " <--> " + user.getRole());
            System.out.println();
            if(u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword()) && u.getRole().equals(user.getRole())){
                System.out.println("User Found. Generating token . . .");

                Claims claims = Jwts.claims()
                        .setSubject(user.getEmail());
                //claims.put("password", user.getPassword());
                claims.put("role", user.getRole());


                return Jwts.builder()
                        .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS512, "inther")
                        .compact();
                }
        }
        System.out.println("User Not Found ! ! !");
        return "No such user";
    }
}
