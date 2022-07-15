package com.bajiuqu.manage.system.user.service.impl;

import com.bajiuqu.common.utils.UUIDUtil;
import com.bajiuqu.manage.system.user.entity.UserDO;
import com.bajiuqu.manage.system.user.repository.UserRepository;
import com.bajiuqu.manage.system.user.service.UserService;
import com.bajiuqu.manage.system.user.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description UserServiceImpl
 * @author 小艺小艺
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserVO> getUserAll(String userType) {
        List<UserDO> userDOS = userRepository.findAllByUserTypeAndUserStatus(userType, "1");
        List<UserVO> userVOS = new ArrayList<>();
        BeanUtils.copyProperties(userDOS, userVOS);
        return userVOS;
    }
}
