package com.example.demo2;

import com.example.demo2.model.Role;
import com.example.demo2.repositoryDao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo2Application implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(Demo2Application.class, args);
    }

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role admin = roleRepository.findByRole("USER");
        Role user = roleRepository.findByRole("ADMIN");

        if(admin == null) {
            roleRepository.save(new Role("USER"));
        }
        if(user == null) {
            roleRepository.save(new Role("ADMIN"));
        }
    }
}
