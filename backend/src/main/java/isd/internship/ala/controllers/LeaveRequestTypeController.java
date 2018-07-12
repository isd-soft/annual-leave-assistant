package isd.internship.ala.controllers;

import isd.internship.ala.models.LeaveRequestType;
import isd.internship.ala.services.LeaveRequestTypeService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public ResponseEntity<LeaveRequestType> getById(@PathVariable("id") int id) {
        return new ResponseEntity<>(leaveRequestTypeService.getById(id), HttpStatus.OK);
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

        return new ResponseEntity<>("Leave request type saved", HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<String, String>> update(@PathVariable("id") Integer id,@RequestBody LeaveRequestType leaveRequestType) {
        HashMap<String, String> response = new HashMap<>();

        response.put("message", "Updated successfuly");
        leaveRequestTypeService.update(id, leaveRequestType);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
