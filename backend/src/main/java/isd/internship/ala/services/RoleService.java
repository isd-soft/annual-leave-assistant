package isd.internship.ala.services;

import isd.internship.ala.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role getById(Integer id);
    Role create(Role status);
    Role update(Role status, Integer id);
    void deleteById(Integer id);
    void deleteAll();

}
