package isd.internship.ala.services;

import isd.internship.ala.models.Holiday;

import java.util.List;

public interface HolidayService {
    List<Holiday> getAll();
    Holiday getById(Integer id);
    Holiday create(Holiday holiday);
    Holiday update(Holiday holiday, Integer id);
    void deleteById(Integer id);
    void deleteAll();
}
