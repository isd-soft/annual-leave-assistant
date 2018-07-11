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

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        leaveRequestTypeService.getById(id);

        return new ResponseEntity<>("Request leave request type with id " + id, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRequestType(@PathVariable("id") int id) {
        System.out.println("Deleting request type with id " + id);

        leaveRequestTypeService.deleteById(id);

        return new ResponseEntity<>("Request type has been deleted", HttpStatus.OK);
    }

    @PostMapping("/cr")
    public ResponseEntity<String> create(@RequestBody LeaveRequestType leaveRequestType) {
        leaveRequestTypeService.create(leaveRequestType);

        return new ResponseEntity<>("Leave request type saved", HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<LeaveRequestType> update(@PathVariable("id") int id, @RequestBody LeaveRequestType leaveRequestType) {
        leaveRequestTypeService.update(id, leaveRequestType);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
