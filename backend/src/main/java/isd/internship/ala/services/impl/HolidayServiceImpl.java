package isd.internship.ala.services.impl;

import isd.internship.ala.models.Holiday;
import isd.internship.ala.repositories.HolidayRepository;
import isd.internship.ala.services.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    @Override
    public List<HashMap<String, String>> getAll() {
        List<HashMap<String, String>> result = new ArrayList<>();
        List<Holiday> holidays = holidayRepository.findAll();

        for(Holiday h : holidays){
            HashMap<String, String> holiday = new HashMap<>();
            holiday.put("id", h.getId().toString());
            holiday.put("name", h.getName());
            holiday.put("date", h.getDate().toString());

            result.add(holiday);
        }
        return result;
    }

    @Override
    public Holiday getById(Integer id) { return holidayRepository.findById(id).get(); }

    @Override
    public Holiday create(Holiday holiday) {
        return holidayRepository.save(holiday);
    }

    @Override
    public Holiday update(Integer id, Holiday holiday) {
        return holidayRepository.save(holiday);
    }


    @Override
    public void deleteById(Integer id) {
        holidayRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        holidayRepository.deleteAll();
    }
}
