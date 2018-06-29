package com.example.demo2.Service;

import com.example.demo2.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UserDAO {

    User findByEmail(String email);
    User saveUser(User user);
    void deleteUser(User user);
    User findUserbyId(long id);


}
