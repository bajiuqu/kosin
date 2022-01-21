package com.bajiuqu.manage.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userController")
public class UserController {

    @GetMapping("/getUser")
    public String getUser() {
        return "1";
    }

}
