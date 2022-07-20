package com.bajiuqu.myuser.controller;

import com.bajiuqu.common.utils.XyBeanUtils;
import com.bajiuqu.common.validated.ValidatedGroup;
import com.bajiuqu.common.vo.ResponseResult;
import com.bajiuqu.myuser.common.dto.UserDTO;
import com.bajiuqu.myuser.common.vo.UserVO;
import com.bajiuqu.myuser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小艺小艺
 * @Description 用户信息类
 */
@Slf4j
@RestController
@RequestMapping("/userController")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户信息
     *
     * @param userDTO 用户信息传输对象
     * @return 成功条数
     */
    @PostMapping("/insert")
    public ResponseResult insert(@Validated({ValidatedGroup.Insert.class}) @RequestBody UserDTO userDTO,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseResult.validateFail(bindingResult.getAllErrors(), ",");
        }
        return ResponseResult.choose(userService.insert(userDTO));
    }

    /**
     * 根据用户编码删除信息
     *
     * @param userCode 用户编码
     * @return 删除结果
     */
    @DeleteMapping("/delete/{userCode}")
    public ResponseResult delete(@PathVariable(name = "userCode") Long userCode) {
        return ResponseResult.choose(userService.delete(userCode));
    }

    /**
     * 更新用户信息
     *
     * @param userDTO
     * @param bindingResult
     * @return
     */
    @PostMapping("/update")
    public ResponseResult update(@Validated({ValidatedGroup.Update.class}) @RequestBody UserDTO userDTO,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseResult.validateFail(bindingResult.getAllErrors(), ",");
        }
        return ResponseResult.choose(userService.update(userDTO));
    }

    /**
     * 查询所有用户：如果有参数则指定类型
     *
     * @return 指定类型的所有用户
     */
    @GetMapping("/getUserAll")
    public ResponseResult getUserAll() {
        List<UserDTO> userDTOList = userService.getAll();
        List<UserVO> userVOList = new ArrayList<>();
        XyBeanUtils.copyCollection(userDTOList, userVOList, UserVO.class);
        return ResponseResult.success(userVOList);
    }

}
