package com.bajiuqu.manage.system.controller;

import client.Userclient;
import com.bajiuqu.common.vo.ResponseResult;
import com.bajiuqu.manage.common.client.UserclientApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/UserTestController")
public class UserTestController {

    @Autowired
    private UserclientApi userclientApi;

    @RequestMapping(value = "/getUserAll", method = RequestMethod.GET)
    public ResponseResult test() {
        return userclientApi.getUserAll("1");
    }

}
