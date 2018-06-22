package com.isd.leaveassistant.service;

import com.isd.leaveassistant.model.Status;
import com.isd.leaveassistant.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> getAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status getById(Integer id) { return statusRepository.getOne(id); }

    @Override
    public Status create(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status update(Status status, Integer id) {
        Status statusNew = statusRepository.getOne(id);
        statusNew.setName(status.getName());
        statusRepository.save(statusNew);
        return statusNew;
    }

    @Override
    public void deleteById(Integer id) {
        statusRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        statusRepository.deleteAll();
    }
}
