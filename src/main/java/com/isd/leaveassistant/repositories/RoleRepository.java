package com.isd.leaveassistant.repositories;

import com.isd.leaveassistant.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);
    Role findRoleById(int id);

}
