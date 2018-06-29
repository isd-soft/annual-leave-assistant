package com.example.demo2.Service;

import com.example.demo2.Service.RoleDAO;
import com.example.demo2.model.Role;
import com.example.demo2.repositoryDao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findRole(String name) {
        return roleRepository.findByRole(name);
    }
}
