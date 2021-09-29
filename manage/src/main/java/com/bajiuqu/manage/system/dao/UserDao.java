package com.bajiuqu.manage.system.dao;

import com.bajiuqu.manage.base.dao.BaseDao;
import com.bajiuqu.manage.system.entity.UserEntity;

import java.util.Map;

public interface UserDao extends BaseDao<UserEntity> {

    UserEntity queryUserByName(Map map);

}
