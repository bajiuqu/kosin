package com.bajiuqu.common.entity;

import com.bajiuqu.common.constant.BaseStatusConstant;
import com.bajiuqu.common.utils.CurrentUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体属性
 * @MappedSuperclass 解决 JPA 创建表字段时，不创建 BaseDO 中的字段
 * @author 小艺小艺
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建人", notes = "创建人")
    @Column(name = "creator")
    public String creator;

    @ApiModelProperty(value = "创建人编码", notes = "创建人编码")
    @Column(name = "creator_code")
    public String creatorCode;

    @ApiModelProperty(value = "创建时间", notes = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "create_date")
    public Date createDate;

    @ApiModelProperty(value = "更新人", notes = "更新人")
    @Column(name = "refresher")
    public String refresher;

    @ApiModelProperty(value = "更新人编码", notes = "更新人编码")
    @Column(name = "refresher_code")
    public String refresherCode;

    @ApiModelProperty(value = "更细时间", notes = "更细时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "update_date")
    public Date updateDate;

    @ApiModelProperty(value = "数据状态", notes = "数据状态: 0:删除; 1:新增; 2:更新;")
    @Column(name = "status")
    public Integer status;

    /**
     * 添加创建人信息
     */
    public void buildInsertData() {
        Date date = new Date();
        this.createDate = date;
        this.updateDate = date;
        this.creator = CurrentUtil.getUserName();
        this.creatorCode = CurrentUtil.getUserCode();
        this.status = BaseStatusConstant.STATUS_NORMAL;
    }

    /**
     * 添加修改人等信息
     */
    public void buildUpdateData() {
        this.updateDate = new Date();
        this.refresher = CurrentUtil.getUserName();
        this.refresherCode = CurrentUtil.getUserCode();
    }

    /**
     * 删除修改人等信息
     */
    public void buildDeleteData() {
        this.updateDate = new Date();
        this.refresher = CurrentUtil.getUserName();
        this.refresherCode = CurrentUtil.getUserCode();
        this.status = BaseStatusConstant.STATUS_DELETED;
    }

}
