package com.bajiuqu.myuser.controller;

import com.bajiuqu.myuser.service.OrganizationService;
import com.bajiuqu.myuser.service.impl.OrganizationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/organizationController")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

}
