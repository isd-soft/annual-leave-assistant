package isd.internship.ala.services;

import isd.internship.ala.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    HashMap<String, String> getUsers();
    User findById(Long id);
}
