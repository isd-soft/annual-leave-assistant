package isd.internship.ala.services.impl;

import isd.internship.ala.models.Status;
import isd.internship.ala.repositories.StatusRepository;
import isd.internship.ala.services.StatusService;
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
    public Status getByName(String name) {
        List<Status> statuses = statusRepository.findAll();
        for(Status status : statuses){
            if(status.getName().equals(name))
                return status;
        }

        return null;
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