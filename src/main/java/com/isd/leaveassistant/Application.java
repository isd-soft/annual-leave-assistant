package com.isd.leaveassistant;

import com.isd.leaveassistant.model.Role;
import com.isd.leaveassistant.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args)  {
        SpringApplication.run(Application.class, args);
    }

    private RoleRepository roleRepository;

    public Application(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = roleRepository.findByRole("ADMIN");
        Role roleUser = roleRepository.findByRole("USER");

        if (roleAdmin == null && roleUser == null) {
            roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));
        }
    }
}
