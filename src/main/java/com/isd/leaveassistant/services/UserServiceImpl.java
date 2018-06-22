package com.isd.leaveassistant.services;

import com.isd.leaveassistant.model.User;
import com.isd.leaveassistant.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder ) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        Iterable<User> allUsers = userRepository.findAll();
        return (ArrayList<User>) allUsers;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        userRepository.save(user);
    }
}
