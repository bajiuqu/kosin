package com.bajiuqu.manage.system.service;

import com.bajiuqu.manage.base.service.BaseService;
import com.bajiuqu.manage.system.entity.UserEntity;

import java.util.Map;

public interface UserService extends BaseService<UserEntity> {

    public UserEntity queryUserByName(Map<String, String> map) throws IllegalAccessException;

}
