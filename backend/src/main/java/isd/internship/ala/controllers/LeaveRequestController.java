package isd.internship.ala.controllers;


import isd.internship.ala.models.LeaveRequest;
import isd.internship.ala.models.Status;
import isd.internship.ala.models.User;
import isd.internship.ala.services.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ala/leaveRequests")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @RequestMapping(value = "/all/{statusId}", method = RequestMethod.GET)
    public List<LeaveRequest> getAllPending(@PathVariable(name = "statusId") Status statusId) {
        return leaveRequestService.findAllPending(statusId);
    }

    @RequestMapping(value = "/all/{userId}/{statusId}", method = RequestMethod.GET)
    public List<LeaveRequest> getAllPendingByUserId(@PathVariable(name = "userId") User userId, @PathVariable(name = "statusId") Status statusId) {
        return leaveRequestService.findAllPendingByUserId(userId, statusId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<LeaveRequest> getAll() {
        return leaveRequestService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public LeaveRequest getById(@PathVariable(name = "id") Integer id) {
        return leaveRequestService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(name = "id") Integer id) {
        leaveRequestService.deleteById(id);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public LeaveRequest create (@RequestBody LeaveRequest leaveRequest) {
        return leaveRequestService.create(leaveRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@RequestBody LeaveRequest leaveRequest, @PathVariable(name = "id") Integer id) {
        leaveRequestService.update(leaveRequest, id);
    }
}
