package com.bajiuqu.manage.system.user.controller;

import com.bajiuqu.common.group.ValidatedGroup;
import com.bajiuqu.common.vo.ResponseResult;
import com.bajiuqu.manage.system.user.dto.UserDTO;
import com.bajiuqu.manage.system.user.service.UserService;
import com.bajiuqu.manage.system.user.vo.UserVO;
import com.bajiuqu.manage.config.PropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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

    @PostMapping("/insert")
    public ResponseResult insert(@Validated({ValidatedGroup.Insert.class}) @RequestBody UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            String defaultMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ResponseResult.invalid(defaultMessage);
        }

        log.warn("用户信息: {}", userDTO.toString());

        return ResponseResult.choose(userService.insert(userDTO));
    }

    /**
     * @param userType 0:后天管理用户；1:系统用户
     * @return
     */
    @GetMapping("/getUserAll/{userType}")
    public ResponseResult getUserAll(@PathVariable("userType") String userType) {
        List<UserVO> userVOS = userService.getUserAll(userType);

        return ResponseResult.success(userVOS);
    }

    @GetMapping(value = "/getProperties")
    public ResponseResult getProperties() {

        return ResponseResult.success(propertiesConfig.getDomainName() + "---" + propertiesConfig.getFilePath() + "---" + propertiesConfig.getEmail());
    }

}
