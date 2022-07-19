package com.bajiuqu.common.entity.base;

import com.bajiuqu.common.constant.BaseStatusConstant;
import com.bajiuqu.common.utils.CurrentUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Table;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体属性
 *
 * @author 小艺小艺
 */
@Getter
@Setter
@ToString
public class BaseDO implements Serializable {

    @ApiModelProperty(value = "创建人", notes = "创建人")
    @Column(name = "creator")
    private String creator;

    @ApiModelProperty(value = "创建人编码", notes = "创建人编码")
    @Column(name = "creator_code")
    private String creatorCode;

    @ApiModelProperty(value = "创建时间", notes = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "create_date")
    private Date createDate;

    @ApiModelProperty(value = "更新人", notes = "更新人")
    @Column(name = "refresher")
    private String refresher;

    @ApiModelProperty(value = "更新人编码", notes = "更新人编码")
    @Column(name = "refresher_code")
    private String refresherCode;

    @ApiModelProperty(value = "更细时间", notes = "更细时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "update_date")
    private Date updateDate;

    @ApiModelProperty(value = "数据状态", notes = "数据状态")
    @Column(name = "status")
    private Integer status;

    /**
     * 添加创建人信息
     */
    public void buildInsertData() {
        Date date = new Date();
        this.status = BaseStatusConstant.STATUS_NORMAL;
        this.createDate = date;
        this.updateDate = date;
        this.creator = CurrentUtil.getUserName();
        this.creatorCode = CurrentUtil.getUserCode();
    }

    /**
     * 添加修改人等信息
     */
    public void buildUpdateData() {
        Date date = new Date();
        this.updateDate = date;
        this.refresher = CurrentUtil.getUserName();
        this.refresherCode = CurrentUtil.getUserCode();
    }

}
