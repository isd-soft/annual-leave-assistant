package com.isd.annual_leave_assistant.service;

import com.isd.annual_leave_assistant.model.Status;

import java.util.List;

public interface StatusService {

    List<Status> getAll();
    Status getById(Integer id);
    Status create(Status status);
    Status update(Status status, Integer id);
    void deleteById(Integer id);
    void deleteAll();

}
