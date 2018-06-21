package com.isd.annual_leave_assistant.controller;

import com.isd.annual_leave_assistant.model.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.isd.annual_leave_assistant.service.LeaveRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/leaveRequests")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
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
