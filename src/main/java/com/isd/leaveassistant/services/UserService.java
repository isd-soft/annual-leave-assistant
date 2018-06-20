package com.isd.leaveassistant.services;

import com.isd.leaveassistant.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(long id);
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
