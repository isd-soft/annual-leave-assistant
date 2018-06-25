package isd.internship.ala.services;

import isd.internship.ala.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
