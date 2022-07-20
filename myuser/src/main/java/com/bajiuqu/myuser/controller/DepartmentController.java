package com.bajiuqu.myuser.controller;

import com.bajiuqu.myuser.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/departmentController")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

}
