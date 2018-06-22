package com.isd.leaveassistant.controller;

import com.isd.leaveassistant.model.Group;
import com.isd.leaveassistant.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Group> getAll() {
        return groupService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Group getById(@PathVariable(name = "id") Integer id) {
        return groupService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(name = "id") Integer id) {
        groupService.deleteById(id);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Group create (@RequestBody Group group) {
        return groupService.create(group);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@RequestBody Group group, @PathVariable(name = "id") Integer id) {
        groupService.update(group, id);
    }

}
