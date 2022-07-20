package com.bajiuqu.myuser.service.impl;

import com.bajiuqu.myuser.dao.DepartmentDao;
import com.bajiuqu.myuser.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

}
