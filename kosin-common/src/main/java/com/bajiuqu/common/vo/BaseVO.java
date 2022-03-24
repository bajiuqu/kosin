package com.bajiuqu.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 小艺小艺
 */
@Data
public class BaseVO implements Serializable {

    @ApiModelProperty(value = "创建人", notes = "创建人")
    private String creator;

    @ApiModelProperty(value = "创建人编码", notes = "创建人编码")
    private String creatorCode;

    @ApiModelProperty(value = "创建时间", notes = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty(value = "更新人", notes = "更新人")
    private String refresher;

    @ApiModelProperty(value = "更新人编码", notes = "更新人编码")
    private String refresherCode;

    @ApiModelProperty(value = "更细时间", notes = "更细时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty(value = "数据状态", notes = "数据状态")
    private Integer status;

}
