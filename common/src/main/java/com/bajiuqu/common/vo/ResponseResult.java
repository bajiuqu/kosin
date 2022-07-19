package com.bajiuqu.common.vo;


import cn.hutool.http.HttpStatus;
import com.bajiuqu.common.constant.ResponseMessageConstant;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ruimi
 */
@Getter
@Setter
public class ResponseResult {

    /**
     * 状态
     */
    private StatusEnum status;
    /**
     * 响应码
     */
    private Integer statusCode;
    /**
     * 响应信息
     */
    private String messages;
    /**
     * 传输数据
     */
    private Object data;

    enum StatusEnum {
        SUCCESS,
        ERROR
    }

    private ResponseResult() {
    }

    private ResponseResult(StatusEnum status, Integer statusCode, String messages, Object data) {
        this.status = status;
        this.statusCode = statusCode;
        this.messages = messages;
        this.data = data;
    }

    public static ResponseResult success() {
        return new ResponseResult(StatusEnum.SUCCESS, HttpStatus.HTTP_OK, ResponseMessageConstant.MSG_SUCCESS, null);
    }

    public static ResponseResult success(String messages) {
        return new ResponseResult(StatusEnum.SUCCESS, HttpStatus.HTTP_OK, messages, null);
    }

    public static ResponseResult success(Object data) {
        return new ResponseResult(StatusEnum.SUCCESS, HttpStatus.HTTP_OK, ResponseMessageConstant.MSG_SUCCESS, data);
    }

    public static ResponseResult success(Integer statusCode, String messages, Object data) {
        return new ResponseResult(StatusEnum.SUCCESS, statusCode, messages, data);
    }

    public static ResponseResult success(String messages, Object data) {
        return new ResponseResult(StatusEnum.SUCCESS, HttpStatus.HTTP_OK, messages, data);
    }

    public static ResponseResult error() {
        return new ResponseResult(StatusEnum.ERROR, HttpStatus.HTTP_INTERNAL_ERROR, ResponseMessageConstant.MSG_ERROR, null);
    }

    public static ResponseResult error(String messages) {
        return new ResponseResult(StatusEnum.ERROR, HttpStatus.HTTP_INTERNAL_ERROR, messages, null);
    }

    public static ResponseResult error(Integer statusCode, String messages) {
        return new ResponseResult(StatusEnum.ERROR, statusCode, messages, null);
    }


    /**
     * 根据返回的记录数目来决定返回成功还是失败,一般用于新增,修改,删除
     *
     * @return
     */
    public static ResponseResult choose(int num) {
        if (num > 0) {
            return success(HttpStatus.HTTP_OK, ResponseMessageConstant.MSG_SUCCESS, null);
        } else {
            return fail();
        }
    }

    /**
     * 400 参数错误或数据不完整,缺少必填项,格式错误等等
     *
     * @return
     */
    public static ResponseResult invalid() {
        return new ResponseResult(StatusEnum.ERROR, HttpStatus.HTTP_BAD_REQUEST
                , ResponseMessageConstant.MSG_INVALID_DATA, "");
    }

    /**
     * 400 参数错误或数据不完整,缺少必填项,格式错误等等
     *
     * @return
     */
    public static ResponseResult invalid(String messages) {
        return new ResponseResult(StatusEnum.ERROR, HttpStatus.HTTP_BAD_REQUEST
                , messages, "");
    }

    /**
     * 403 无权限访问的接口,数据或者数据对当前用户是不允许操作
     *
     * @return
     */
    public static ResponseResult deny() {
        return new ResponseResult(StatusEnum.ERROR, HttpStatus.HTTP_FORBIDDEN
                , ResponseMessageConstant.MSG_DENY_MODIFY, "");
    }

    /**
     * 405 数据重复了
     *
     * @return
     */
    public static ResponseResult duplicate() {
        return new ResponseResult(StatusEnum.ERROR, HttpStatus.HTTP_BAD_METHOD
                , ResponseMessageConstant.MSG_DENY_DUPLICATE, "");
    }

    /**
     * HttpStatus.HTTP_NOT_FOUND 预期可以得到数据却未得到,数据可能已经被删除或不在可操作的状态
     *
     * @return
     */
    public static ResponseResult notFound() {
        return new ResponseResult(StatusEnum.ERROR, HttpStatus.HTTP_NOT_FOUND
                , ResponseMessageConstant.MSG_NOT_FOUND, "");
    }

    /**
     * 修改,插入,删除等修改记录条数为0
     *
     * @return
     */
    public static ResponseResult fail() {
        return new ResponseResult(StatusEnum.ERROR, HttpStatus.HTTP_NOT_FOUND
                , ResponseMessageConstant.MSG_FAIL, "");
    }

    /**
     * 修改,插入,删除等修改记录条数为0
     *
     * @return
     */
    public static ResponseResult fail(String msg) {
        return new ResponseResult(StatusEnum.ERROR, HttpStatus.HTTP_NOT_FOUND
                , msg, "");
    }

    public boolean isSuccess() {
        return this.statusCode == HttpStatus.HTTP_OK;
    }

}
