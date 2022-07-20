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

@ApiModel(value = "组织机构实体")
@Setter
@Getter
@ToString
@Entity
@Table(name = "sys_organization")
public class OrganizationDO extends BaseDO {

    @Id
    @Column(name = "org_code")
    private Long orgCode;

    @Column(name = "org_name")
    private Long orgName;

    @Column(name = "parent_code")
    private Long parentCode;

}
