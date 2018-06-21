package com.isd.annual_leave_assistant.repository;

import com.isd.annual_leave_assistant.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository <LeaveRequest, Integer> {

    List<LeaveRequest> findAllByUserIdEqualsOrderByRequestDate (Integer id);
    List<LeaveRequest> findAllByUserIdEqualsAndStatusIdEquals (Integer userId, Integer statusId);
    //List<LeaveRequest> findAllByStatusIdEqualsAndUserIdEqualsAndOrderByRequestDate (Integer statusId, Integer userId );
    List<LeaveRequest> findAllByStatusIdEqualsOrderByRequestDate (Integer statusId);

}
