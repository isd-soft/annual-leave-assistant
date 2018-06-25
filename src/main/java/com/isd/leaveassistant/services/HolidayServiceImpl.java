package com.isd.leaveassistant.services;

import com.isd.leaveassistant.model.Holiday;
import com.isd.leaveassistant.repositories.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    @Override
    public List<Holiday> getAll() {
        return holidayRepository.findAll();
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
