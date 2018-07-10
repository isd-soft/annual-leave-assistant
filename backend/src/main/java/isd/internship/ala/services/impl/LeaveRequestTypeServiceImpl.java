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
    public LeaveRequestType getById(int id) { return leaveRequestTypeRepository.findById(id).get(); }

    @Override
    public LeaveRequestType create(LeaveRequestType leaveRequestType) {
        return leaveRequestTypeRepository.save(leaveRequestType);

    }

    @Override
    public LeaveRequestType update(int id, LeaveRequestType leaveRequestType) {
        LeaveRequestType leaveRequestTypeNew = leaveRequestTypeRepository.findById(id).get();

        leaveRequestTypeNew.setName(leaveRequestType.getName());
        leaveRequestTypeNew.setDescription(leaveRequestType.getDescription());

        return leaveRequestTypeRepository.save(leaveRequestTypeNew);
    }

    @Override
    public void deleteById(int id) {
        leaveRequestTypeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        leaveRequestTypeRepository.deleteAll();
    }

    @Override
    public LeaveRequestType findByName(String name) {
        List<LeaveRequestType> leaveRequestTypes = leaveRequestTypeRepository.findAll();
        for(LeaveRequestType leaveRequestType : leaveRequestTypes){
            if(leaveRequestType.getName().equals(name))
                return leaveRequestType;
        }
        return null;
    }
}
