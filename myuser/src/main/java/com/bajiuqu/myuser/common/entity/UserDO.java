package com.bajiuqu.myuser.common.entity;

import com.bajiuqu.common.constant.BaseStatusConstant;
import com.bajiuqu.common.entity.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 小艺小艺
 */
@ApiModel(value = "用户实体")
@Getter
@Setter
@ToString
@Entity
@Table(name = "sys_user_info")
public class UserDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * @GeneratedValue 主键自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_code")
    @ApiModelProperty(value = "用户主键", notes = "用户主键")
    private Long userCode;

    @ApiModelProperty(value = "用户姓名", notes = "用户姓名")
    @Column(name = "user_name")
    private String userName;

    @ApiModelProperty(value = "用户登录名", notes = "用户登录名")
    @Column(name = "user_login_name")
    private String userLoginName;

    @ApiModelProperty(value = "用户登录密码", notes = "用户登录密码")
    @Column(name = "user_login_password")
    private String userLoginPassword;

    @ApiModelProperty(value = "用户生辰", notes = "用户生辰")
    @Column(name = "user_birthdate")
    private Date UserBirthdate;

    @ApiModelProperty(value = "用户年龄", notes = "用户年龄")
    @Column(name = "user_age")
    private Integer userAge;

    @ApiModelProperty(value = "用户类型：0:后台管理用户；1:系统用户", notes = "用户类型：0:后台管理用户；1:系统用户")
    @Column(name = "user_type")
    private String userType;

    @ApiModelProperty(value = "用户状态：1:可用；0:冻结", notes = "用户状态：1:可用；0:冻结")
    @Column(name = "user_status")
    private Integer userStatus;

    @ApiModelProperty(value = "用户角色ID", notes = "用户角色ID")
    @Column(name = "user_role_id")
    private String userRoleId;

    public UserDO() {
        super();
        this.userStatus = BaseStatusConstant.STATUS_NORMAL;
    }

}
