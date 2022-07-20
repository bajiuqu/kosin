package com.bajiuqu.myuser.common.entity;

import com.bajiuqu.common.entity.BaseDO;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(value = "部门实体")
@Getter
@Setter
@ToString
@Entity
@Table(name = "sys_department")
public class DepartmentDO extends BaseDO {

    @Id
    @Column(name = "dept_code")
    private Long deptCode;

    @Column(name = "dept_name")
    private Long deptName;

    @Column(name = "parent_code")
    private Long parentCode;

}
