package com.bajiuqu.manage.system.user.entity;

import com.bajiuqu.common.constant.BaseStatusConstant;
import com.bajiuqu.common.entity.base.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 小艺小艺
 */
@ApiModel(value = "用户实体")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "SYS_USER_INFO")
public class UserDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(value = "用户主键", notes = "用户主键")
    @Column(name = "USER_ID")
    private String userId;

    @ApiModelProperty(value = "创建人", notes = "创建人")
    @Column(name = "CREATOR")
    private String creator;

    @ApiModelProperty(value = "创建人编码", notes = "创建人编码")
    @Column(name = "CREATOR_CODE")
    private String creatorCode;

    @ApiModelProperty(value = "创建时间", notes = "创建时间")
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @ApiModelProperty(value = "更新人", notes = "更新人")
    @Column(name = "REFRESHER")
    private String refresher;

    @ApiModelProperty(value = "更新人编码", notes = "更新人编码")
    @Column(name = "REFRESHER_CODE")
    private String refresherCode;

    @ApiModelProperty(value = "更细时间", notes = "更细时间")
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @ApiModelProperty(value = "数据状态", notes = "数据状态")
    @Column(name = "STATUS")
    private Integer status;

    @ApiModelProperty(value = "用户姓名", notes = "用户姓名")
    @Column(name = "USER_NAME")
    private String userName;

    @ApiModelProperty(value = "用户登录名", notes = "用户登录名")
    @Column(name = "USER_LOGIN_NAME")
    private String userLoginName;

    @ApiModelProperty(value = "用户登录密码", notes = "用户登录密码")
    @Column(name = "USER_LOGIN_PASSWORD")
    private String userLoginPassword;

    @ApiModelProperty(value = "用户生辰", notes = "用户生辰")
    @Column(name = "USER_BIRTHDATE")
    private Date UserBirthdate;

    @ApiModelProperty(value = "用户年龄", notes = "用户年龄")
    @Column(name = "USER_AGE")
    private Integer userAge;

    @ApiModelProperty(value = "用户类型：0:后台管理用户；1:系统用户", notes = "用户类型：0:后台管理用户；1:系统用户")
    @Column(name = "USER_TYPE")
    private String userType;

    @ApiModelProperty(value = "用户状态：1:可用；0:冻结", notes = "用户状态：1:可用；0:冻结")
    @Column(name = "USER_STATUS")
    private Integer userStatus;

    public UserDO() {
        super();
        this.userStatus = BaseStatusConstant.STATUS_NORMAL;
    }

}
