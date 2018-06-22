package com.isd.leaveassistant.controller;

import com.isd.leaveassistant.model.User;
import com.isd.leaveassistant.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public  String registrationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "user/registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationProcess(User user, BindingResult bindingResult, Model model) {
        User userExist = userService.findUserByEmail(user.getEmail());
        if (userExist != null) {
            bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
        }

        if (bindingResult.hasErrors()) {
            return "user/registration";
        }
        else {
            userService.saveUser(user);
            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());
        }


        return "redirect:/user/login";
    }

    @RequestMapping(value = "/profilepage/{id}", method = RequestMethod.GET)
    public String profilePage(Model model,@PathVariable("id") int id) {

        model.addAttribute("user", userService.getUserById(id));

        return "user/profilepage";
    }
}
