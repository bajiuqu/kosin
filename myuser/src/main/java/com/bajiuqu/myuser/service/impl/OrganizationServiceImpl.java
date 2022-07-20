package com.bajiuqu.myuser.service.impl;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleCreateTableStatement;
import com.bajiuqu.myuser.common.dto.OrganizationDTO;
import com.bajiuqu.myuser.common.query.OrganizationQuery;
import com.bajiuqu.myuser.dao.OrganizationDao;
import com.bajiuqu.myuser.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public int insert(OrganizationDTO organization) {
        return 0;
    }

    @Override
    public int delete(Long aLong) {
        return 0;
    }

    @Override
    public int update(OrganizationDTO organization) {
        return 0;
    }

    @Override
    public List<OrganizationDTO> getAll() {
        return null;
    }

    @Override
    public List<OrganizationDTO> getAllByQuery(OrganizationQuery organizationQuery) {
        return null;
    }

}
