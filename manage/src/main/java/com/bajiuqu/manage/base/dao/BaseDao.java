package com.bajiuqu.manage.base.dao;

import java.util.List;

public interface BaseDao<T> {

    int add(T t);

    void del(Integer id);

    void up(T t);

    List<T> sel(Integer id);

}
