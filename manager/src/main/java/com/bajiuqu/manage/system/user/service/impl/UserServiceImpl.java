package com.bajiuqu.manage.system.user.service.impl;

import com.bajiuqu.manage.common.entity.UserDO;
import com.bajiuqu.manage.system.user.dao.UserDao;
import com.bajiuqu.manage.system.user.dto.UserDTO;
import com.bajiuqu.manage.system.user.repository.UserRepository;
import com.bajiuqu.manage.system.user.service.UserService;
import com.bajiuqu.manage.system.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description UserServiceImpl
 * @author 小艺小艺
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserVO> getUserAll(String userType) {
//        有问题：java.lang.IllegalArgumentException: Parameter value [1] did not match expected type [java.lang.Integer
//        List<UserDO> userDOS = userRepository.findAllByUserTypeAndUserStatus(userType, "1");

        Example example = new Example(UserDO.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userStatus", 1);
        criteria.andEqualTo("userType", userType);
        List<UserDO> userDOS = userDao.selectByExample(example);

        List<UserVO> userVOS = new ArrayList<>();
        BeanUtils.copyProperties(userDOS, userVOS);
        return userVOS;
    }

    @Override
    public int insert(UserDTO userDTO) {

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        userDO.buildInsertData();
        return userDao.insert(userDO);
    }

}
