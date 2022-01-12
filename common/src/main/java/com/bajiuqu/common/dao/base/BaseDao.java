package com.bajiuqu.common.dao.base;

import java.util.List;

/**
 * 基础 DAO 接口
 *
 * @param <T> 实体类类型
 * @author 小艺小艺
 */
public interface BaseDao<T> {

    /**
     * 插入数据
     *
     * @param t 实体类数据
     * @return 执行记录数量
     */
    int insert(T t);

    /**
     * 插入实体类中非空的属性值
     *
     * @param t 实体类数据
     * @return 执行记录数量
     */
    int insertEntitySelective(T t);

    /**
     * 根据主键删除数据
     *
     * @param id 实体类 ID
     */
    void deleteByPrimaryKey(Integer id);

    /**
     * 更新数据
     *
     * @param t 实体类数据
     */
    void update(T t);

    /**
     * 根据主键查询数据
     *
     * @param id 实体类 ID
     * @return 执行记录集合
     */
    List<T> selectByPrimaryKey(Integer id);

}
