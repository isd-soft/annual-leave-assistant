package isd.internship.ala.services;

import isd.internship.ala.models.Holiday;

import java.util.HashMap;
import java.util.List;

public interface HolidayService {
    List<Holiday> getAll();
    Holiday getById(Integer id);
    Holiday create(Holiday holiday);
    Holiday update(Integer id, Holiday holiday);
    void deleteById(Integer id);
    void deleteAll();
}
