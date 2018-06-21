package com.isd.annual_leave_assistant.controller;


import com.isd.annual_leave_assistant.model.LeaveRequestType;
import com.isd.annual_leave_assistant.service.LeaveRequestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/leaveRequestTypes")
public class LeaveRequestTypeController {

    @Autowired
    private LeaveRequestTypeService leaveRequestTypeService;

    public LeaveRequestTypeController(LeaveRequestTypeService leaveRequestTypeService) {
        this.leaveRequestTypeService = leaveRequestTypeService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<LeaveRequestType> getAll() {
        return leaveRequestTypeService.getAll();
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
