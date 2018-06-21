package com.isd.annual_leave_assistant.repository;

import com.isd.annual_leave_assistant.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository <Status, Integer> {

}
