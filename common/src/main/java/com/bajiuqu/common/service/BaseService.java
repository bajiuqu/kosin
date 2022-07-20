package com.bajiuqu.common.service;

import java.util.List;

/**
 * 基础 seivice, CRUD
 *
 * @param <E> 元素类型
 * @param <N> 主键类型
 */
public interface BaseService<E, N, T> {

    /**
     * 添加用户信息
     *
     * @param e 信息传输对象
     * @return 添加成功条数
     */
    int insert(E e);

    /**
     * 根据标识删除信息
     *
     * @param n 主键
     * @return 删除结果条数
     */
    int delete(N n);

    /**
     * 修改信息
     *
     * @param e 信息传输对象
     * @return 更新结果条数
     */
    int update(E e);

    /**
     * 查询全部
     *
     * @return 全部数据
     */
    List<E> getAll();

    /**
     * 根据查询对象查询数据
     *
     * @param t 数据查询对象
     * @return 查询结果集
     */
    List<E> getAllByQuery(T t);

}
