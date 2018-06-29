package com.example.demo2.Service;

import com.example.demo2.model.LeaveRequest;
import com.example.demo2.repositoryDao.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LeaveRequestDAOImpl implements LeaveRequestDAO {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Override
    public List<LeaveRequest> findByUser(long id) {
        return leaveRequestRepository.findByUserId(id);
    }
}
