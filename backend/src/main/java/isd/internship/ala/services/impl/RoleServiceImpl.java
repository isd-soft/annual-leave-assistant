package isd.internship.ala.services.impl;

import isd.internship.ala.models.Role;
import isd.internship.ala.repositories.RoleRepository;
import isd.internship.ala.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(Integer id) { return roleRepository.getOne(id); }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role, Integer id) {
        Role roleNew = roleRepository.getOne(id);
        roleNew.setRole(role.getRole());
        roleRepository.save(roleNew);
        return roleNew;
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }
}
