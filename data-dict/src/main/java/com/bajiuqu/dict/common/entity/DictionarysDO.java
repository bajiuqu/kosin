package com.bajiuqu.dict.common.entity;

import com.bajiuqu.common.entity.BaseDO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 小艺小艺
 */
@Data
@Entity
@Table(name = "sys_dictionary")
public class DictionarysDO extends BaseDO {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "dict_code")
    private String dictCode;

    @Column(name = "dict_name")
    private String dictName;

    @Column(name = "pdict_code")
    private String pdictCode;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "remark")
    private String remark;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "delete_by")
    private String deleteBy;

    @Column(name = "delete_time")
    private Date deleteTime;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "dept_id")
    private String deptId;

    @Column(name = "order_by")
    private Integer orderBy;


}
