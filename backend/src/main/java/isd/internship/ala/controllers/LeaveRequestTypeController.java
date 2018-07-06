package isd.internship.ala.controllers;

import isd.internship.ala.models.LeaveRequestType;
import isd.internship.ala.services.LeaveRequestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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

//    @DeleteMapping("{id}")
//    public void deleteById(@PathVariable(name = "id") int id) {
//        leaveRequestTypeService.deleteById(id);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRequestType(@PathVariable("id") int id) {
        System.out.println("Deleting request type with id " + id);

        leaveRequestTypeService.deleteById(id);

        return new ResponseEntity<>("Request type has been deleted", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody LeaveRequestType leaveRequestType) {
        leaveRequestTypeService.create(leaveRequestType);
        return new ResponseEntity<>("Leave request type saved", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@RequestBody LeaveRequestType leaveRequestType, @PathVariable(name = "id") Integer id) {
        leaveRequestTypeService.update(leaveRequestType, id);
    }

}
