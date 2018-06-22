package com.isd.leaveassistant.controller;

import com.isd.leaveassistant.model.Status;
import com.isd.leaveassistant.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/statuses")
public class StatusController {

    @Autowired
    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Status> getAll() {
        return statusService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Status getById(@PathVariable(name = "id") Integer id) {
        return statusService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(name = "id") Integer id) {
        statusService.deleteById(id);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Status create (@RequestBody Status status) {
        return statusService.create(status);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@RequestBody Status status, @PathVariable(name = "id") Integer id) {
        statusService.update(status, id);
    }

}
