package com.isd.leaveassistant.repository;

import com.isd.leaveassistant.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository <Status, Integer> {

}
