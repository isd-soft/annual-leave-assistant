package com.isd.leaveassistant;

import com.isd.leaveassistant.model.Role;
import com.isd.leaveassistant.model.User;
import com.isd.leaveassistant.repositories.RoleRepository;
import com.isd.leaveassistant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Populating database to test stuff
        LocalDate date = LocalDate.of(2018,1,1);
        roleRepository.save(new Role("Admin"));
        roleRepository.save(new Role("User"));
        userRepository.save(new User("Name", "Surname", "Name.Surname@gmail.com", "password", new Date(), 1, roleRepository.findRoleById(1)));

    }
}
