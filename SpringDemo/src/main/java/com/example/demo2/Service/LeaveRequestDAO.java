package com.example.demo2.Service;

import com.example.demo2.model.LeaveRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface LeaveRequestDAO {

    List<LeaveRequest> findByUser(long id);
}
