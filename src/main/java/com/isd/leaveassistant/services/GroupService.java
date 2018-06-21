package com.isd.annual_leave_assistant.service;

import com.isd.annual_leave_assistant.model.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAll();
    Group getById(Integer id);
    Group create(Group group);
    Group update(Group group, Integer id);
    void deleteById(Integer id);
    void deleteAll();

}
