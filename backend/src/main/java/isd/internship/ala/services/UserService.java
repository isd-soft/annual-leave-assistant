package isd.internship.ala.services;

import isd.internship.ala.models.User;

<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.HashMap;
>>>>>>> jwtImpl
import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findByEmail(String email);
    List<User> findAll();
<<<<<<< HEAD
    List<User> findAllwoRole();
=======
    HashMap<String, String> getUsers();
>>>>>>> jwtImpl
    User findById(Long id);
}
