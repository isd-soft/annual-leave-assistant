package com.isd.leaveassistant.repositories;

import com.isd.leaveassistant.model.LeaveRequest;
import com.isd.leaveassistant.model.Status;
import com.isd.leaveassistant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository <LeaveRequest, Integer> {

    List<LeaveRequest> findAllByUserIdEqualsOrderByRequestDate (User id);
    List<LeaveRequest> findAllByUserIdEqualsAndStatusIdEquals (User userId, Status statusId);
    List<LeaveRequest> findAllByStatusIdEqualsOrderByRequestDate (Status statusId);

}
