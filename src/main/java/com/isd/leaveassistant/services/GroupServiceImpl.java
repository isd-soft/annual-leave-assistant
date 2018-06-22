package com.isd.leaveassistant.service;

import com.isd.leaveassistant.model.Group;
import com.isd.leaveassistant.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group getById(Integer id) { return groupRepository.getOne(id); }

    @Override
    public Group create(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group update(Group group, Integer id) {
        Group groupNew = groupRepository.getOne(id);
        groupNew.setName(group.getName());
        groupRepository.save(groupNew);
        return groupNew;
    }

    @Override
    public void deleteById(Integer id) {
        groupRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        groupRepository.deleteAll();
    }
}
