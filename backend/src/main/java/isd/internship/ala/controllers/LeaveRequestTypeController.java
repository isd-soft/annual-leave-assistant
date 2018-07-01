package isd.internship.ala.controllers;

import isd.internship.ala.models.LeaveRequestType;
import isd.internship.ala.services.LeaveRequestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ala/leaveRequestTypes")
public class LeaveRequestTypeController {

    @Autowired
    private LeaveRequestTypeService leaveRequestTypeService;

    @GetMapping
    public ResponseEntity<List<LeaveRequestType>> getAll() {
        return ResponseEntity.status(200).body(leaveRequestTypeService.getAll());
    }




    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public LeaveRequestType getById(@PathVariable(name = "id") Integer id) {
        return leaveRequestTypeService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(name = "id") Integer id) {
        leaveRequestTypeService.deleteById(id);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public LeaveRequestType create (@RequestBody LeaveRequestType leaveRequestType) {
        return leaveRequestTypeService.create(leaveRequestType);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@RequestBody LeaveRequestType leaveRequestType, @PathVariable(name = "id") Integer id) {
        leaveRequestTypeService.update(leaveRequestType, id);
    }

}
