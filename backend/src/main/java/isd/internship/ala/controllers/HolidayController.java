package isd.internship.ala.controllers;

import isd.internship.ala.models.Holiday;
import isd.internship.ala.services.HolidayService;
import isd.internship.ala.services.TokenService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/ala/holidays")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @Autowired
    private TokenService tokenService;

    public HolidayController(HolidayService holidayService) {

        this.holidayService = holidayService;
    }

    // GET holiday list
    @GetMapping( produces = "application/json")
    public ResponseEntity<List<HashMap<String, String>>> getAll(@RequestHeader(value = "Authorization") String header) {
        if (tokenService.isAdmin(header))
            return ResponseEntity.status(200).body(holidayService.getAll());
        else
            return ResponseEntity.status(403).body(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Holiday getById(@PathVariable(name = "id") Integer id) {

        return holidayService.getById(id);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Holiday> create(@RequestBody Holiday holiday) {
        return ResponseEntity.status(201).body(holidayService.create(holiday));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteHoliday(@PathVariable("id") int id) {

        holidayService.deleteById(id);

        return new ResponseEntity<>("Holiday has been deleted", HttpStatus.OK);
    }

//    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
//    public void update(@RequestBody Holiday holiday, @PathVariable(name = "id") Integer id) {
//        holidayService.update(holiday, id);
//    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Holiday> update(@PathVariable("id") Integer id, @RequestBody Holiday holiday) {
        holidayService.update(id, holiday);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
