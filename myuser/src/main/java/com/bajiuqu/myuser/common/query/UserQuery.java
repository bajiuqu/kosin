package com.bajiuqu.myuser.common.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserQuery implements Serializable {

    @ApiModelProperty(value = "用户编码", notes = "用户编码")
    private Long userCode;

}
