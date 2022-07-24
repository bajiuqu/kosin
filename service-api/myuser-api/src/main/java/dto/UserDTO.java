package dto;

import com.bajiuqu.common.entity.BaseDO;
import com.bajiuqu.common.validated.ValidatedGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息传输对象
 *
 * @author ruimi
 * @date 2022-07-18
 */
@Getter
@Setter
@ToString
public class UserDTO extends BaseDO implements Serializable {

    @NotNull(groups = {ValidatedGroup.Update.class}, message = "更新用户，用户主键不允许为空")
    @ApiModelProperty(value = "用户姓名", notes = "用户姓名")
    private Long userId;

    @NotNull(groups = {ValidatedGroup.Insert.class}, message = "注册用户，用户姓名不允许为空")
    @ApiModelProperty(value = "用户姓名", notes = "用户姓名")
    private String userName;

    @NotNull(groups = {ValidatedGroup.Insert.class}, message = "注册用户，用户登录名不允许为空")
    @ApiModelProperty(value = "用户登录名", notes = "用户登录名")
    private String userLoginName;

    @NotNull(groups = {ValidatedGroup.Insert.class}, message = "注册用户，用户密码不允许为空")
    @ApiModelProperty(value = "用户登录密码", notes = "用户登录密码")
    private String userLoginPassword;

    @NotNull(groups = {ValidatedGroup.Insert.class}, message = "注册用户，用户生辰不允许为空")
    @ApiModelProperty(value = "用户生辰", notes = "用户生辰")
    private Date userBirthdate;

    @NotNull(groups = {ValidatedGroup.Insert.class}, message = "注册用户，用户类型不允许为空")
    @ApiModelProperty(value = "用户类型：0:后台管理用户；1:系统用户", notes = "用户类型：0:后台管理用户；1:系统用户")
    private String userType;

}
