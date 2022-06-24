package com.bajiuqu.common.enums;

import lombok.Getter;

/**
 * 全局返回值状态信息类
 *
 * @author 小艺小艺
 */
@Getter
public enum ResultEnum {

    SUCCESS(200, "成功"),
    ABSENCE(204, "资源不存在"),
    PARAMERROR(406, "请求参数错误"),
    ERROR(500, "失败");

    // 状态码
    private Integer code;
    // 状态消息
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
