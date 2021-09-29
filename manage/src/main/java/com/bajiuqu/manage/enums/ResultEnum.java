package com.bajiuqu.manage.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(200, "成功"),
    ABSENCE(204, "资源不存在"),
    PARAMERROR(406, "请求参数错误"),
    ERROR(500, "失败")
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
