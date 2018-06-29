package com.example.demo2.Service;

import com.example.demo2.model.Role;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleDAO {

    Role findRole(String name);
}
