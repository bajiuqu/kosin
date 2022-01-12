package com.bajiuqu.manage.system.repository;

import com.bajiuqu.common.dao.base.BaseDao;
import com.bajiuqu.manage.system.entity.UserDO;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends BaseDao<UserDO> {



}
