package com.bajiuqu.dict.entity;

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
@Table(name = "SYS_DICTIONARY")
public class DictionarysDO implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "DICT_CODE")
    private String dictCode;

    @Column(name = "DICT_NAME")
    private String dictName;

    @Column(name = "PDICT_CODE")
    private String pdictCode;

    @Column(name = "GROUP_ID")
    private String groupId;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_BY")
    private String updateBy;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "DELETE_BY")
    private String deleteBy;

    @Column(name = "DELETE_TIME")
    private Date deleteTime;

    @Column(name = "ORG_ID")
    private String orgId;

    @Column(name = "DEPT_ID")
    private String deptId;

    @Column(name = "ORDER_BY")
    private Integer orderBy;

    @Column(name = "IDSTR")
    private String idstr;

}
