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
        for(Holiday holiday: holidays){
            HashMap<String, String> day = new HashMap<>();
            day.put("name", holiday.getName());
            day.put("date", holiday.getDate().toString());
            result.add(day);
        }
        return result;
    }

    @Override
    public Holiday getById(Integer id) { return holidayRepository.getOne(id); }

    @Override
    public Holiday create(Holiday holiday) {
        return holidayRepository.save(holiday);
    }

    @Override
    public Holiday update(Holiday holiday, Integer id) {
        Holiday holidayNew = holidayRepository.getOne(id);
        holidayNew.setName(holiday.getName());
        holidayRepository.save(holidayNew);
        return holidayNew;
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
