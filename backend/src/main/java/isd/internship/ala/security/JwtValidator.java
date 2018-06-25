package isd.internship.ala.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import isd.internship.ala.models.User;
import isd.internship.ala.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtValidator {
    private String secret = "inther";

    public User validate(String token) {

        User user = null;
        try {
            System.out.println("V A L I D A T I N G . . .");
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            String username = body.getSubject();
            String password = (String) body.get("password");
            String role = (String) body.get("role");

            user = new User();
            user.setEmail(username);
            user.setPassword(password);
            user.setRole(role);

        }
        catch (Exception e) {
            System.out.println(e);
        }

        return user;
    }
}
