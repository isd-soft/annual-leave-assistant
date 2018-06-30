package isd.internship.ala.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import isd.internship.ala.models.User;
import org.springframework.stereotype.Component;

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

            String email = (String) body.get("email");

            user = new User(email, "pass");

        } catch(SignatureException e) {
            System.out.println(e);
        }

        return user;
    }
}
