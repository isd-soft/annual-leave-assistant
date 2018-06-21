package com.isd.annual_leave_assistant.repository;

import com.isd.annual_leave_assistant.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository <Group, Integer> {

}
