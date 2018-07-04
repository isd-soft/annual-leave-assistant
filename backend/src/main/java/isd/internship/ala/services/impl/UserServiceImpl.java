package isd.internship.ala.services.impl;

import isd.internship.ala.models.User;
import isd.internship.ala.repositories.UserRepository;
import isd.internship.ala.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }


    // Get user list w/o roles
    @Override
    public HashMap<String, String> getUsers() {
        HashMap<String, String> user = new HashMap<>();
        List<User> users = userRepository.findAll();

        for(User u : users){
            user.put("id", u.getId().toString());
            user.put("surname", u.getSurname());
            user.put("name", u.getName());
            user.put("email", u.getSurname());
            user.put("empDate", u.getEmpDate().toString());
            System.out.println(u);
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public ResponseEntity<String> deleteUser(long id) {
        userRepository.deleteById(id);
        return null;
    }

    @Override
    public ResponseEntity<String> deleteAllUsers() {
        userRepository.deleteAll();
        return null;
    }


}
