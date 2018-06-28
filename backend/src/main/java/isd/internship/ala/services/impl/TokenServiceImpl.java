package isd.internship.ala.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import isd.internship.ala.models.Role;
import isd.internship.ala.models.User;
import isd.internship.ala.repositories.RoleRepository;
import isd.internship.ala.services.TokenService;
import isd.internship.ala.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public boolean isAdmin(String header) {
        String token = header.substring(6);
        Claims body = Jwts.parser()
                .setSigningKey("inther")
                .parseClaimsJws(token)
                .getBody();

        String subject = body.getSubject();

        System.out.println("Subject: " + subject);

        try {
            Role adminRole = roleRepository.findByRole("ADMIN");
            User user = userService.findByEmail(subject).get();

            System.out.println("Role: " + adminRole.getRole());

            if((user.getRole()).getId().equals(adminRole.getId())) {
                System.out.println("Access granted");
                return true;
            } else {
                System.out.println("Access restricted!");
                return false;
            }
        } catch(NoSuchElementException e) {
            System.out.println("No such element exception !");
            return false;
        }
    }
}
