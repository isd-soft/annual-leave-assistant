package com.example.demo2.controller;


import com.example.demo2.model.Role;
import com.example.demo2.Service.RoleDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class RoleController {

    @Autowired
    RoleDAOImpl roleDAO;

    @GetMapping("/role/{name}")
    private Role getRole(@PathVariable(value = "name") String name) {

        Role role = roleDAO.findRole(name);
        System.out.println(role.getRole());

        return role;
    }


}
