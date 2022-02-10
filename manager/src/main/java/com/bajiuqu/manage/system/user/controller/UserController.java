package com.bajiuqu.manage.system.user.controller;

import com.bajiuqu.manage.system.user.service.UserService;
import com.bajiuqu.manage.system.user.vo.UserVO;
import com.bajiuqu.manage.config.PropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小艺小艺
 * @Description 用户信息类
 */
@Slf4j
@RestController
@RequestMapping("/userController")
public class UserController {

    @Resource(name = "PropertiesConfig")
    private PropertiesConfig propertiesConfig;

    @Autowired
    private UserService userService;

    /**
     *
     * @param userType 0:后天管理用户；1:系统用户
     * @return
     */
    @GetMapping("/getUserAll/{userType}")
    public UserVO getUserAll(@PathVariable("userType") String userType) {
        userService.getUserAll(userType);

        return null;
    }

    @GetMapping(value = "/getProperties")
    public String getProperties() {
        return propertiesConfig.getDomainName() + "---" + propertiesConfig.getFilePath() + "---" + propertiesConfig.getEmail();
    }

}
