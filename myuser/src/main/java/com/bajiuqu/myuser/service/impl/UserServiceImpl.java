package com.bajiuqu.myuser.service.impl;

import com.bajiuqu.common.utils.XyBeanUtils;
import com.bajiuqu.myuser.common.query.UserQuery;
import com.bajiuqu.myuser.dao.UserDao;
import com.bajiuqu.myuser.common.dto.UserDTO;
import com.bajiuqu.myuser.common.entity.UserDO;
import com.bajiuqu.myuser.repository.UserRepository;
import com.bajiuqu.myuser.service.UserService;
import com.bajiuqu.myuser.common.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private UserDao userDao;

    @Resource
    private UserRepository userRepository;

    @Override
    public int insert(UserDTO userDTO) {
        UserDO userDO = XyBeanUtils.copyProperties(userDTO, UserDO.class);
        userDO.buildInsertData();
        return userDao.insertSelective(userDO);
    }

    @Override
    public int delete(Long userCode) {
        try {
            UserDO userDO = new UserDO();
            userDO.buildDeleteData();
            return userDao.updateByPrimaryKeySelective(userDO);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(UserDTO userDTO) {
        try {
            UserDO userDO = XyBeanUtils.copyProperties(userDTO, UserDO.class);
            userDO.buildUpdateData();
            return userDao.updateByPrimaryKeySelective(userDO);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDO> userDOS = userRepository.findAllByUserTypeAndUserStatus("1", 1);
        List<UserDTO> userVOS = new ArrayList<>();
        XyBeanUtils.copyCollection(userDOS, userVOS, UserDTO.class);
        return userVOS;
    }

    @Override
    public List<UserDTO> getAllByQuery(UserQuery userQuery) {
        return null;
    }

}
