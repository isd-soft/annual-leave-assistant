package com.isd.leaveassistant.repositories;

import com.isd.leaveassistant.model.LeaveRequestType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestTypeRepository extends JpaRepository <LeaveRequestType, Integer> {

}
