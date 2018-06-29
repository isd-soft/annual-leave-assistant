package com.example.demo2.Service;

import com.example.demo2.Service.UserDAO;
import com.example.demo2.model.User;
import com.example.demo2.repositoryDao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDAOImpl implements UserDAO {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findUserbyId(long id) {
        return userRepository.findById(id).get();
    }



}
