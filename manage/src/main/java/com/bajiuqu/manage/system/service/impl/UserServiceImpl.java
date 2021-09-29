package com.bajiuqu.manage.system.service.impl;

import com.bajiuqu.manage.system.dao.UserDao;
import com.bajiuqu.manage.system.entity.UserEntity;
import com.bajiuqu.manage.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.*;
//import java.util.Optional;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    /**
     * <p>
     * 编程式事务
     * 1、TransactionTemplate
     * 2、PlatformTransactionManager 事务上层的管理者
     * DefaultTransactionDefinition 定义事务的传播行为、隔离级别、超时时间
     * TransactionStatus 事务的运行状态
     * <p>
     * 声明式事务
     *
     * @Transactional propagation：设置事务传播机制
     * 1、REQUIRED：
     * 支持当前事务，如果当前没有事务，则新建事务；
     * 如果当前存在事务，则加入当前事务，合并成一个事务。
     * 2、REQUIRES_NEW：
     * 新建事务，如果当前存在事务，则把当前事务挂起；
     * 此方法会独立提交事务，不受调用者的事务影响，父级影响，它也是正常提交
     * 3、NESTED：
     * 如果当前存在事务，它将会成为父级事务的一个子事务，方法结束事务不会提交，只有父级事务结束之后它才会提交
     * 如果当前没有事务，则新建事务
     * 如果此事务异常。父级可以捕捉它的异常而不进行回滚，正常提交
     * 如果父级异常，它必然回滚
     * 4、SUPPORTS
     * 如果当前存在事务，则加入事务
     * 如果不存在事务，则以非事务的方式运行
     * 5、NOT_SUPPORTED
     * 以非事务方式运行
     * 如果当前存在事务，则把当前事务挂起
     * 6、MANDATORY
     * 如果当前存在事务，则运行在当前事务中
     * 如果当前无事务，则抛出异常，父级方法必须有事务
     * 7、NEVER
     * 以非事务方式运行，如果当前存在事务，则抛出异常，父级方法必须没有事务
     * 参数：
     * rollbackFor：遇到异常，事务回滚
     * propagation：事务传播机制
     * isolation：事务隔离级别
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @Override
    public int add(UserEntity userEntity) throws InterruptedException {
//        transactionTemplate.execute(transactionStatus -> {
//            try {
//                userEntity.setCreateDate(new Date());
//                if (null == userEntity.getId())
//                    userEntity.setUpdateDate(new Date());
//                int insert = userDao.add(userEntity);
//                System.out.println(insert);
//                return 1;
//            } catch (Exception e) {
//                transactionStatus.setRollbackOnly();
//                e.printStackTrace();
//                return 0;
//            }
//        });
        /**
         * getPropagationBehavior(); 返回事务的传播行为
         * getIsolationLevel(); 返回事务的隔离级别
         * getTimeout(); 返回事务的超时时间
         * isReadOnly(); 返回事务是否为只读
         */
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();

        /**
         * isNewTransaction(); 是否是新的事务
         * hasSavepoint(); 是否有恢复点
         * setRollbackOnly(); 设置为只回滚
         * isRollbackOnly(); 是否为只回滚
         * isCompleted(); 是否已完成
         */
        TransactionStatus transaction = platformTransactionManager.getTransaction(definition);
//        try {
//            userEntity.setCreateDate(new Date());
//            if (null == userEntity.getId())
//                userEntity.setUpdateDate(new Date());
//            int insert = userDao.add(userEntity);
//            System.out.println(insert);
//            platformTransactionManager.commit(transaction);
//        } catch (Exception e) {
//            platformTransactionManager.rollback(transaction);
//        }
        userEntity.setCreateDate(LocalDateTime.now());
        userEntity.setUpdateDate(LocalDateTime.now());
//        Thread.sleep(10000);
        int result = userDao.add(userEntity);
        return result;
    }

    @Override
    public void del(Integer id) {
    }

    @Override
    public void up(UserEntity userEntity) {

    }

//    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @Override
    public List<UserEntity> sel(Integer id) throws InterruptedException {
        Assert.isTrue(id != null, "请输入正确的 ID ");
        List<UserEntity> result = userDao.sel(id);

        Optional<List<UserEntity>> result1 = Optional.ofNullable(result);

        if (Objects.isNull(result)) {
            List<UserEntity> userEntities = new ArrayList<>();
            return userEntities;
        }
        return result;
    }

    @Override
    public UserEntity queryUserByName(Map<String, String> map) {
        String nickname = map.get("nickname");
        String username = map.get("username");
        if ("".equals(nickname) && "".equals(username)) {
            // 次数应该使用自定义异常处理
            throw new RuntimeException();
        }
        UserEntity userEntity = userDao.queryUserByName(map);
        if (null == userEntity) {
            return new UserEntity();
        }
        return userEntity;
    }
}
