package com.bajiuqu.manage.system.user.repository;

import com.bajiuqu.manage.system.user.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author 小艺小艺
 * @Description UserRepository
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface UserRepository extends JpaRepository<UserDO, Serializable>, JpaSpecificationExecutor<UserDO> {

    /**
     * 查询所有用户
     * @param userType 用户类型
     * @param status 用户状态
     * @return
     */
    List<UserDO> findAllByUserTypeAndUserStatus(String userType, String status);

}
