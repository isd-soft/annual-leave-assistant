package isd.internship.ala.services;

import isd.internship.ala.models.Holiday;

import java.util.HashMap;
import java.util.List;

public interface HolidayService {
    List<HashMap<String, String>> getAll();
    Holiday getById(Integer id);
    Holiday create(Holiday holiday);
    Holiday update(Holiday holiday, Integer id);
    void deleteById(Integer id);
    void deleteAll();
}
