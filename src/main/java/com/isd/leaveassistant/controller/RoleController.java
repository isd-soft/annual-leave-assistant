package com.isd.leaveassistant.controller;

import com.isd.leaveassistant.model.Role;
import com.isd.leaveassistant.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Role> getAll() {
        return roleService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Role getById(@PathVariable(name = "id") Integer id) {
        return roleService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(name = "id") Integer id) {
        roleService.deleteById(id);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Role create (@RequestBody Role role) {
        return roleService.create(role);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@RequestBody Role role, @PathVariable(name = "id") Integer id) {
        roleService.update(role, id);
    }

    @RequestMapping (value = "/query", method = RequestMethod.GET)
    public Role getByIdId (@RequestParam(name = "id") Integer id) {
        return roleService.getById(id);
    }

}
