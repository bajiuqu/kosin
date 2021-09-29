package com.bajiuqu.manage.system.service.impl;

import com.bajiuqu.manage.system.entity.UserEntity;
import com.bajiuqu.manage.system.service.UserService;
import org.apache.shiro.util.Assert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceTwo")
public abstract class UserServiceImplTwo implements UserService {
    @Override
    public int add(UserEntity userEntity) {
        return 0;
    }

    @Override
    public void del(Integer id) {

    }

    @Override
    public void up(UserEntity userEntity) {

    }

    @Override
    public List<UserEntity> sel(Integer id) {
        Assert.isTrue(id == null, "请输入正确的 ID ");
        return null;
    }
}
