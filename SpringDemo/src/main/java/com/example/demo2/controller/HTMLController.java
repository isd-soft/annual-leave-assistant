package com.example.demo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTMLController {

    @RequestMapping(value = "/**")
    public String registration() {
//        User user = new User("ezio125@gmail.com", "Stas", "USER");
//        userDAO.saveUser(user);

        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {

        return "login";
    }
}
