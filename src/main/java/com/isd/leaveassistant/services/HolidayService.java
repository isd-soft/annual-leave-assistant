package com.isd.leaveassistant.services;

import com.isd.leaveassistant.model.Holiday;

import java.util.List;

public interface HolidayService {

    List<Holiday> getAll();
    Holiday getById(Integer id);
    Holiday create(Holiday holiday);
    Holiday update(Holiday holiday, Integer id);
    void deleteById(Integer id);
    void deleteAll();
}
