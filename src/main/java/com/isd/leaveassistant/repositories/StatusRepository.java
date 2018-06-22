package com.isd.leaveassistant.repositories;

import com.isd.leaveassistant.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository <Status, Integer> {

}
