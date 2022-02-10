package com.bajiuqu.manage.system.user.vo;

import com.bajiuqu.common.utils.UUIDUtil;
import com.bajiuqu.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author 小艺小艺
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UserVO extends BaseVO {

    @ApiModelProperty(value = "用户主键", notes = "用户主键")
    private String userId;

    @ApiModelProperty(value = "创建人", notes = "创建人")
    private String creator;

    @ApiModelProperty(value = "创建人编码", notes = "创建人编码")
    private String creatorCode;

    @ApiModelProperty(value = "创建时间", notes = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新人", notes = "更新人")
    private String refresher;

    @ApiModelProperty(value = "更新人编码", notes = "更新人编码")
    private String refresherCode;

    @ApiModelProperty(value = "更细时间", notes = "更细时间")
    private Date updateDate;

    @ApiModelProperty(value = "数据状态", notes = "数据状态")
    private Integer status;

    @ApiModelProperty(value = "用户姓名", notes = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "用户登录名", notes = "用户登录名")
    private String userLoginName;

    @ApiModelProperty(value = "用户登录密码", notes = "用户登录密码")
    private String userLoginPassword;

    @ApiModelProperty(value = "用户生辰", notes = "用户生辰")
    private Date UserBirthdate;

    @ApiModelProperty(value = "用户年龄", notes = "用户年龄")
    private Integer userAge;

    @ApiModelProperty(value = "用户类型：0:后台管理用户；1:系统用户", notes = "用户类型：0:后台管理用户；1:系统用户")
    private String userType;

    @ApiModelProperty(value = "用户状态：1:可用；0:冻结", notes = "用户状态：1:可用；0:冻结")
    private Integer userStatus;

}
