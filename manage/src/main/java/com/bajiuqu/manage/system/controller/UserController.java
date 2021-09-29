package com.bajiuqu.manage.system.controller;

import com.bajiuqu.manage.enums.ResultEnum;
import com.bajiuqu.manage.system.entity.UserEntity;
import com.bajiuqu.manage.system.service.UserService;
import com.bajiuqu.manage.base.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;

@Scope(value = "prototype")
@Controller
@RequestMapping("/UserController")
public class UserController {

    private int num = 0;
    private final ThreadLocal<Integer> numInt = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return num;
        }
    };

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Result addUser(@RequestBody UserEntity userEntity) {
        int result = 0;
        try {
            result = userService.add(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (0 == result)
            return new Result(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage());
        return new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage() + " : " + result);
    }

    @ResponseBody
    @RequestMapping(value = "/sel", method = RequestMethod.GET)
    public Result sel(@Nullable Integer id) {
        List<UserEntity> sel = null;
        try {
            sel = userService.sel(id);
            if (sel.size() == 0) {
                return new Result(ResultEnum.ABSENCE.getCode(), ResultEnum.ABSENCE.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage());
        }
        return new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), sel, sel.size());
    }

    @ResponseBody
    @GetMapping(value = "/queryUserByName")
    public Result queryUserByName(@RequestParam(value = "nickname", required = false) String nickname,
                                  @RequestParam(value = "username", required = false) String username) {
        // regex 正则规则
        Map<String, String> map = new HashMap<>();
        if (!Objects.isNull(nickname) && !"".equals(username)) {
            nickname = nickname.trim();
            map.put("nickname", nickname);
        }
        if (!Objects.isNull(username) && !"".equals(username)) {
            username = username.trim();
            map.put("username", username);
        }
        try {
            UserEntity user = userService.queryUserByName(map);
            return new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), Arrays.asList(user));
        } catch (Exception e) {
            return new Result(ResultEnum.PARAMERROR.getCode(), ResultEnum.PARAMERROR.getMessage());
        }
    }



}
