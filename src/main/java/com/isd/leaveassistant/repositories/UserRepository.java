package com.isd.leaveassistant.repositories;

import com.isd.leaveassistant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);
}
