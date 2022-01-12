package com.bajiuqu.manage.system.entity;

import com.bajiuqu.common.entity.base.BaseDO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "yf_fq_users")
public class UserDO extends BaseDO {

    @Id
    private Integer fuId;

}
