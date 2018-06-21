package com.isd.leaveassistant.services;

import com.isd.leaveassistant.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(long id);
    User findUserByEmail(String email);
    void saveUser(User user);
}
