package com.bajiuqu.manage.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer code;// 返回码
    private String message;//返回信息
    private Object data;// 返回数据
    private Integer count;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, Object data, Integer count) {
        this.code = code;
        this.data = data;
        this.count = count;
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
