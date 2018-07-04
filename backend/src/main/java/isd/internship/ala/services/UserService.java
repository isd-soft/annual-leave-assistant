package isd.internship.ala.services;

import isd.internship.ala.models.User;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    HashMap<String, String> getUsers();
    User findById(Long id);
    ResponseEntity<String> deleteUser(long id);
    ResponseEntity<String> deleteAllUsers();

}
