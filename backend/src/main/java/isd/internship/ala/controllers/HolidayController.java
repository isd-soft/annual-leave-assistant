package isd.internship.ala.controllers;

import isd.internship.ala.models.Holiday;
import isd.internship.ala.services.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ala/holidays")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {

        this.holidayService = holidayService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Holiday> getAll() {

        return holidayService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Holiday getById(@PathVariable(name = "id") Integer id) {

        return holidayService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(name = "id") Integer id) {

        holidayService.deleteById(id);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Holiday create (@RequestBody Holiday holiday) {

        return holidayService.create(holiday);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@RequestBody Holiday holiday, @PathVariable(name = "id") Integer id) {
        holidayService.update(holiday, id);
    }

}
