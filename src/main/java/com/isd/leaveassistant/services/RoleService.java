package com.isd.leaveassistant.services;

import com.isd.leaveassistant.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAll();
    Role getById(Integer id);
    Role create(Role status);
    Role update(Role status, Integer id);
    void deleteById(Integer id);
    void deleteAll();

}
