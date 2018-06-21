package com.isd.annual_leave_assistant.repository;

import com.isd.annual_leave_assistant.model.LeaveRequestType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestTypeRepository extends JpaRepository <LeaveRequestType, Integer> {

}
