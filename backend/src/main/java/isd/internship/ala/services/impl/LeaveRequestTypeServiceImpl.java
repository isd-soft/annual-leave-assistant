package isd.internship.ala.services.impl;

import isd.internship.ala.models.LeaveRequestType;
import isd.internship.ala.repositories.LeaveRequestTypeRepository;
import isd.internship.ala.services.LeaveRequestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestTypeServiceImpl implements LeaveRequestTypeService {

    @Autowired
    private LeaveRequestTypeRepository leaveRequestTypeRepository;

    @Override
    public List<LeaveRequestType> getAll() {
        return leaveRequestTypeRepository.findAll();
    }

    @Override
    public LeaveRequestType getById(Integer id) { return leaveRequestTypeRepository.getOne(id); }

    @Override
    public LeaveRequestType create(LeaveRequestType leaveRequestType) {
        return leaveRequestTypeRepository.save(leaveRequestType);
    }

    @Override
    public LeaveRequestType update(LeaveRequestType leaveRequestType, Integer id) {
        LeaveRequestType leaveRequestTypeNew = leaveRequestTypeRepository.getOne(id);
        leaveRequestTypeNew.setName(leaveRequestType.getName());
        leaveRequestTypeRepository.save(leaveRequestTypeNew);
        return leaveRequestTypeNew;
    }

    @Override
    public void deleteById(Integer id) {
        leaveRequestTypeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        leaveRequestTypeRepository.deleteAll();
    }
}