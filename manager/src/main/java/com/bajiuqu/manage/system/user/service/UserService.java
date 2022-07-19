package com.bajiuqu.manage.system.user.service;

import com.bajiuqu.manage.system.user.dto.UserDTO;
import com.bajiuqu.manage.system.user.vo.UserVO;

import java.util.List;

/**
 * @Description UserService
 * @author 小艺小艺
 */
public interface UserService {

    List<UserVO> getUserAll(String userType);

    int insert(UserDTO userDTO);

}
