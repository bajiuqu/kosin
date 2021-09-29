package com.bajiuqu.manage.base.service;

import java.util.List;

public interface BaseService<T> {

    int add(T t) throws Exception;

    void del(Integer id) throws Exception;

    void up(T t) throws Exception;

    List<T> sel(Integer id) throws Exception;

}
