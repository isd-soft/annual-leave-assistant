package com.isd.leaveassistant.repositories;

import com.isd.leaveassistant.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
    Role findRoleById(Integer id);

}
