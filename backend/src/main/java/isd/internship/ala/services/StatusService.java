package isd.internship.ala.services;

import isd.internship.ala.models.Status;

import java.util.List;

public interface StatusService {
    List<Status> getAll();
    Status getById(Integer id);
    Status create(Status status);
    Status update(Status status, Integer id);
    void deleteById(Integer id);
    void deleteAll();
}
