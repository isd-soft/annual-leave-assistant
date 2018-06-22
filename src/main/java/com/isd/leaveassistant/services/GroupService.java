package com.isd.leaveassistant.service;

import com.isd.leaveassistant.model.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAll();
    Group getById(Integer id);
    Group create(Group group);
    Group update(Group group, Integer id);
    void deleteById(Integer id);
    void deleteAll();

}
