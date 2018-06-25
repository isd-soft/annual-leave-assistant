package com.isd.leaveassistant.repositories;

import com.isd.leaveassistant.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
}
