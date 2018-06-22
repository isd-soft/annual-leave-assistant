package com.isd.leaveassistant.repository;

import com.isd.leaveassistant.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository <LeaveRequest, Integer> {

    List<LeaveRequest> findAllByUserIdEqualsOrderByRequestDate (Integer id);
    List<LeaveRequest> findAllByUserIdEqualsAndStatusIdEquals (Integer userId, Integer statusId);
    List<LeaveRequest> findAllByStatusIdEqualsOrderByRequestDate (Integer statusId);

}
